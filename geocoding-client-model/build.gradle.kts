plugins {
    java
	id("maven-publish")
}

repositories {
	maven {
		name = "GitHubPackages"
		url = uri("https://maven.pkg.github.com/16000198/geocoding")
		credentials {
			username = System.getenv("GITHUB_TOKEN")
			password = System.getenv("GITHUB_TOKEN")
		}
	}
}

publications {
	register("gpr") {
		from(components["java"])
	}
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
