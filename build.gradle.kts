subprojects {
	group = "io.slick.metcast"
	version = "1.0"
	plugins {
		maven-publish
	}
	repositories {
		mavenCentral()
		maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/16000198/geocoding")
            credentials {
                username = System.getenv("GITHUB_TOKEN")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
	}
	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
	}
	publications {
        register("gpr") {
            from(components["java"])
        }
    }
}
