int data = 0;
int index = 0;

int speedDirectionPin = 12;
int speedVelocityPin = 5;

int dirDirectionPin = 13;
int dirVelocityPin = 6;

void setup() {
 Serial.begin(9600);
 pinMode(speedDirectionPin, OUTPUT);
 pinMode(speedVelocityPin, OUTPUT);
 pinMode(dirDirectionPin, OUTPUT);
 pinMode(dirVelocityPin, OUTPUT);
}

void setVelocity(int dir, int value) {
  if(dir == 1) {
    digitalWrite(speedDirectionPin, HIGH);
  }
  else {
    digitalWrite(speedDirectionPin, LOW);
  }
  Serial.print("Speed dir: ");
  Serial.println(dir);
  Serial.print("Speed value: ");
  Serial.println(value);
  analogWrite(speedVelocityPin, value);
}

void setDirection(int dir, int value) {
  if(dir == 1) {
    digitalWrite(dirDirectionPin, HIGH);
  }
  else {
    digitalWrite(dirDirectionPin, LOW);
  }
  Serial.print("Direction dir: ");
  Serial.println(dir);
  Serial.print("Direction value: ");
  Serial.println(value);
  digitalWrite(dirVelocityPin, value);
}

void parseData(int index, int data){
  //setting speed
  if(index == 0){
    if(data >= 128) {
      setVelocity(1, (data - 128)*2);
    }
    else {
      setVelocity(0, (127-data)*2);
    }
  }
  else if(index == 1){
    if(data >= 128) {
      setDirection(1, HIGH);
    }
    else {
      setDirection(0, LOW);
    }
  }
}

void loop() {
  data = Serial.read();
  Serial.println(data);
  if(data != -1) {
    parseData(index,data);
    index++;
    index = index % 2;
  }
  delay(20);
}
