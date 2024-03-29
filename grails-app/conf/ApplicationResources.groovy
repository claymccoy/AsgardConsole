modules = {

    console {
        dependsOn 'app, codeMirrorGroovy, sparkline'
    }

    app {
        dependsOn 'jquery, angularResource, bootstrap'
        resource url: 'css/app.css'
        resource url: 'js/angular/app/js/angularApp.js'
    }

    bootstrap {
        dependsOn 'jquery'
        resource url: 'js/bootstrap/css/bootstrap.min.css'
        resource url: 'js/bootstrap/js/bootstrap.min.js'
    }

    angular {
        resource id: 'js', url: [dir: 'js/angular/app/lib/angular', file: "angular.js"], nominify: true
    }

    angularResource {
        dependsOn 'angular'
        resource id: 'js', url: [dir: 'js/angular/app/lib/angular', file: "angular-resource.js"], nominify: true
    }

    codeMirrorGroovy {
        dependsOn 'codeMirror'
        resource url: 'js/codemirror/mode/groovy/groovy.js'
    }

    codeMirror {
        resource url: 'js/codemirror/lib/codemirror.css'
        resource url: 'js/codemirror/lib/codemirror.js'
    }

    sparkline {
        resource url: 'js/sparkline/jquery.sparkline.min.js'
    }

}
