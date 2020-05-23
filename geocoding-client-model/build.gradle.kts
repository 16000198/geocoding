plugins {
    java
}

java {
	sourceCompatibility = JavaVersion.VERSION_14
	targetCompatibility = JavaVersion.VERSION_14
}

dependencies {
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.10.3")

	compileOnly("org.projectlombok:lombok:1.18.12")
	annotationProcessor("org.projectlombok:lombok:1.18.12")
}
