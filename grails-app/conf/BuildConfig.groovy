if(System.getenv('TRAVIS_BRANCH')) {
    grails.project.repos.grailsCentral.username = System.getenv("GRAILS_CENTRAL_USERNAME")
    grails.project.repos.grailsCentral.password = System.getenv("GRAILS_CENTRAL_PASSWORD")
}

grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolver="maven"
grails.project.repos.default = "calnet-plugins"
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        mavenLocal()
        mavenRepo id: "calnet-repo", url: "https://maven.calnet.berkeley.edu/artifactory/all/"
        jcenter()
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        // Newer dependency for rest-client-builder that includes Grails 2.5
        // fix.  Built from a fork, ucidentity/grails-data-mapping branch
        // 3.x-grails2.5-fix-ucb.
        //
        // Needed until
        // https://github.com/grails/grails-data-mapping/pull/591 is merged
        // into grails/grails-data-mapping and until rest-client-builder
        // authors use a grails-datastore-rest-client version that contains
        // that fix.
        compile 'org.grails:grails-datastore-rest-client:3.1.6-UCB1-BUILD-SNAPSHOT', {
            exclude group:'javax.servlet', name:'javax.servlet-api'
            exclude group:'commons-codec', name:'commons-codec'
            exclude group:'org.grails', name:'grails-plugin-converters'
            exclude group:'org.grails', name:'grails-core'
            exclude group:'org.grails', name:'grails-web'
        }
    }

    plugins {
        build(":release:3.1.1") {
            export = false
            excludes 'rest-client-builder'
        }
    }
}
