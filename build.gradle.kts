import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    java
    //war
    //id("war")
    //id("org.springframework.boot") version "2.2.0.M5"
    //id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.41"
    //kotlin("plugin.spring") version "1.3.41"
    //kotlin("plugin.jpa") version "1.3.41"
    id("maven-publish")
}

group = "work.toolset.code.jvm.web"
version = "2019.8.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8
/*
val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
*/
repositories {
    maven { url = uri("https://mirrors.sjtug.sjtu.edu.cn/maven-central/") }
    maven { url = uri("http://maven.aliyun.com/nexus/content/groups/public/") }
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.okhttp3:okhttp:4.1.0")
    
    /*
    implementation("org.jsoup:jsoup:1.12.1")
    //if the application would be deployed via JAR package
    //please remember to comment providedRuntime spring-boot-starter-tomcat dependency
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-activemq")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-artemis")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-cassandra")
    implementation("org.springframework.boot:spring-boot-starter-data-cassandra-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-couchbase")
    implementation("org.springframework.boot:spring-boot-starter-data-couchbase-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-data-solr")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.springframework.boot:spring-boot-starter-rsocket")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.okta.spring:okta-spring-boot-starter:1.2.1")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.0")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.springframework.session:spring-session-jdbc")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("org.hsqldb:hsqldb")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.batch:spring-batch-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.security:spring-security-test")
    */
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}


configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


//https://docs.gradle.org/current/userguide/publishing_overview.html
//https://www.jianshu.com/p/8aba5768a20b
//https://docs.gradle.org/5.5/userguide/signing_plugin.html
//https://docs.gradle.org/5.5/userguide/publishing_overview.html
tasks.register<Jar>("generateSourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.javadoc {
    val standardJavadocDocletOptions: StandardJavadocDocletOptions =
            options as StandardJavadocDocletOptions
    standardJavadocDocletOptions.memberLevel = JavadocMemberLevel.PROTECTED
    standardJavadocDocletOptions.version()
    standardJavadocDocletOptions.author()
    standardJavadocDocletOptions.header = project.name
    standardJavadocDocletOptions.addStringOption(
            "Xdoclint:none", "-quiet"
    )
    standardJavadocDocletOptions.encoding = "UTF-8"
    standardJavadocDocletOptions.charSet = "UTF-8"
    
    logging.captureStandardError(LogLevel.INFO)
    logging.captureStandardOutput(LogLevel.INFO)
}

tasks.register<Jar>("generateJavadocJar") {
    group = "documentation"
    dependsOn(tasks.javadoc)
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.get().destinationDir)
}


publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
            groupId = project.group as String
            version = project.version as String
            artifactId = project.name
            
            artifact(tasks["generateSourcesJar"])
            artifact(tasks["generateJavadocJar"])
            
            pom {
                name.set(project.name)
                description.set(project.name)
                url.set("https://github.com/app-util")
                licenses {
                    license {
                        name.set("GNU General Public License,Version 3.0")
                        url.set("http://www.gnu.org/licenses/gpl-3.0.txt")
                        //http://www.gnu.org/licenses/gpl-3.0.html
                    }
                }
                developers {
                    developer {
                        id.set("null")
                        name.set("null")
                        email.set("null")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/app-util")
                    developerConnection.set("scm:git:https://github.com/app-util")
                    url.set("https://github.com/app-util")
                }
                
                repositories {
                    
                    //after publishing to GitHub at
                    //https://github.com/$ORG_OR_USERNAME_AS_NAMESPACE$/$REPOSITORY$
                    //the maven repository can be detect at
                    //https://raw.githubusercontent.com/$ORG_OR_USERNAME_AS_NAMESPACE$/$REPOSITORY$/$BRANCH$
                    maven {
                        name = "local"
                        url = uri("file://$buildDir/repo")
                    }
                    
                    maven {
                        name = "sonatype"
                        url = URI.create("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                        credentials {
                            username = "Do you think I will tell you my username?"
                            password = "Do you think I will tell you my password?"
                        }
                    }
                    maven {
                        name = "sonatypeSnapshot"
                        url = URI.create("https://oss.sonatype.org/content/repositories/snapshots/")
                        credentials {
                            username = "Do you think I will tell you my username?"
                            password = "Do you think I will tell you my password?"
                        }
                    }
                }
            }
        }
    }
}


/*
tasks.withType<Test> {
    useJUnitPlatform()
}

//https://guides.gradle.org/writing-gradle-tasks/
//https://tuhrig.de/gradles-bootrun-and-windows-command-length-limit/
//https://stackoverflow.com/questions/38501933/createprocess-error-206-the-filename-or-extension-is-too-long-when-running-gwtc
tasks.register<Jar>("generateRuntimeDependenciesClasspathJar") {
    group = "application"
    description = ""
    
    dependsOn(configurations.runtimeClasspath)
    archiveAppendix.set("runtimeDependenciesClasspath")
    
    doFirst {
        //configurations.runtime.get().files
        println(
                "number of runtime dependencies:" +
                configurations.runtimeClasspath.get().files.size
        )
        val filenameList: MutableList<String> = mutableListOf()
        for (currentFile in configurations.runtimeClasspath.get().files)
        {
            filenameList.add(
                    currentFile.toURI().toURL().toString().replaceFirst("file:/", "/")
            )
        }
        manifest {
            attributes(
                    mapOf<String, String>(
                            Pair(
                                    "Class-Path",
                                    filenameList.joinToString(" ")
                            )
                    )
            )
        }
    }
}
tasks.bootRun {
    dependsOn("generateRuntimeDependenciesClasspathJar")
    doFirst {
        classpath = files(
                "$buildDir/classes/java/main",
                "$buildDir/classes/kotlin/main",
                "$buildDir/resources/main",
                tasks.named<Jar>("generateRuntimeDependenciesClasspathJar")
                        .get().archiveFile.get().asFile
        )
    }
}
*/
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "UTF-8"
}
tasks.compileJava {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "UTF-8"
}
tasks.compileTestJava {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
tasks.compileTestKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
