const int vochtSensorPin = A0;
const int pompPin = 2;
const int vochtigheidsdrempel = 400;

void setup() {
  digitalWrite(pompPin, LOW);
  pinMode(vochtSensorPin, INPUT);
  pinMode(pompPin, OUTPUT);

  // Zorgt ervoor dat de pomp bij het opstarten is uitgeschakeld
  digitalWrite(pompPin, LOW);

  // Start de seriele monitor
  Serial.begin(9600);
}

void loop() {
  // Lees de vochtigheidssensorwaarde
  int vochtigheid = analogRead(vochtSensorPin);

  // Druk de vochtigheid af in de seriele monitor
  Serial.print("Vochtigheid: ");
  Serial.println(vochtigheid);

  // Controleer of de vochtigheid boven de drempel ligt
  if (vochtigheid > vochtigheidsdrempel) {
    // Schakel de pomp in als de grond te droog is
    Serial.println("Grond te droog. Pomp wordt ingeschakeld.");
    digitalWrite(pompPin, HIGH);
    delay(1000);  // Laat de pomp 1 seconde draaien
    digitalWrite(pompPin, LOW);  // Zet de pomp uit
    Serial.println("Pomp uitgeschakeld.");
  }
  else {
    digitalWrite(pompPin, LOW);
    }

  delay(5000);  //
}