subprojects {
	group = "io.slick.metcast"
	version = "1.0"
	repositories {
		mavenCentral()
	}
	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
	}
}
