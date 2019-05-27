//apply {
//    from(project.file("../ktbuild2.gradle.kts"))
//}
plugins {
    id("com.android.application")
    kotlin("android.extensions")
}

task("ktrqq") {
    doLast {
        project.configurations.findByName("")
    }
}

tasks {

}
configurations {

    create("rqq").apply {
        isTransitive = false
    }
}

dependencies {

   }
