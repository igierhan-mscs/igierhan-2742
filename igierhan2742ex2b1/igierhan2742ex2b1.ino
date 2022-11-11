//------------------Ethernet/IP/Http post--------------------
/*-----( Import needed libraries )-----*/
#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = {
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

/*-----( Declare objects )-----*/
IPAddress arduinoIp(192,168,1, 196);
IPAddress gateway(192, 168, 1, 170);
IPAddress subnet(255, 255, 255, 0);
IPAddress serverIp(204, 77, 50, 53);
int serverPort = 80;
char pageName[] = "/propertymonitor/api/sensorreadings";
EthernetClient client;
// Declare http Post request data
char json[41];  // Must be large enough to hold entire JSON payload
//----------------------------------------------------------

#define SENSORID 1035

#define trigPin 6
#define echoPin 5
#define FILTER_N 9

int distances[FILTER_N];
int prevDistance;

void setup() 
{
  Serial.begin (115200);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  //------------------- Start ethernet -------------------
  if (Ethernet.begin(mac) == 0) Ethernet.begin(mac, arduinoIp, gateway, subnet);
  delay(1000);
  //--------------------------------------------
}

void loop() 
{
  //------------------- ex2b -------------------
  Ethernet.maintain();
  //--------------------------------------------
  
  long duration, distance, avgDistance, tolerance;
  for (int i = 0; i < FILTER_N; i++) {
    digitalWrite(trigPin, LOW);        
    delayMicroseconds(2);              
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);           
    digitalWrite(trigPin, LOW);
    duration = pulseIn(echoPin, HIGH);
    distance = (duration/2) / 29.1;
    if (distance < 400 && distance > 0)
    {
      distances[i] = distance;
    }
    delay(5);
  }
  bubbleSort(distances, FILTER_N);
  avgDistance = calcAvg(distances, FILTER_N);
  tolerance = (long) (avgDistance * 0.05);

  if ((avgDistance < prevDistance - tolerance) || (avgDistance > prevDistance + tolerance)) {
    for (int i = 0; i < FILTER_N; i++) {
      Serial.print(distances[i]);
      Serial.print(' ');
    }
    sprintf(json, "{'SensorId':%d,'Value':%d}", SENSORID, avgDistance); // Cast currTempReading to int
    Serial.println(json);
    postPage(json);
    delay(1000);
  }
  prevDistance = avgDistance;
}

void bubbleSort(int arr[], int n)
{
    int i, j;
    for (i = 0; i < n - 1; i++)
        for (j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1]) {
              int temp = arr[j];
              arr[j] = arr[j+1];
              arr[j+1] = temp;
            }
}

int calcAvg(int arr[], int n) {
  int sum = 0;
  for (int i = 1; i < n - 5; i++) {
    sum += arr[i];
  }
  return sum / (n - 6);
}

//------------------- ex2b -------------------
// --------------------------------- postPage()  ---------------------------------
// ---------------------- Performs http Post of json payload ---------------------
byte postPage(char* json)
{
  int inChar;
  Serial.println("postPage() connecting...");

  if (client.connect(serverIp, serverPort)) {
    Serial.println("postPage() connected");

    // send http header
//    client.println("POST /propertymonitor/api/sensorreadings HTTP/1.1");
    client.print("POST ");
    client.print(pageName);
    client.println(" HTTP/1.1");
    client.println("Host: 192.168.1.196"); // or generate from your server variable to not hardwire
    client.println("User-Agent: Arduino/uno ethernet");
    client.println("Connection: close");
    client.println("Content-Type: application/json");
    client.print("Content-Length: ");
    client.println(strlen(json));// number of bytes in the payload
    client.println();// important: need an empty line here
    // send payload
    client.println(json);
  }
  else
  {
    Serial.println(F("postPage() connect failed"));
    return 0;
  }

  int connectLoop = 0;

  while(client.connected())
  {
    while(client.available())
    {
      inChar = client.read();
//      Serial.write(inChar);
      connectLoop = 0;
    }

    delay(1);
    connectLoop++;
    if(connectLoop > 10000)
    {
      Serial.println();
      Serial.println(F("Timeout"));
      client.stop();
    }
  }

  Serial.println(F("disconnecting."));
  Serial.println();
  
  client.stop();
  return 1;
}
  //--------------------------------------------
