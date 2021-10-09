# mutantsDetector
servicio rest que permite verificar una secuencia de adn

Este servicio Rest está desarrollado en spring boot, Maven y firebase

Para comprobar el funcionamiento del codigo se deben seguir los siguientes pasos

 1. Importar el proyecto como un proyecto Maven

 2. Ubicar la clase MutantDetectorApplication

 3. Ejecutarla como una java application

Lo anterior levanta el servidor embebido en spring, una vez que haya terminado de iniciar
ya se pueden realizar peticiones desde algún cliente que permita realizar peticiones
Post como Postman

El tipo de petición debe ser POST, La URL que se debe invocar es la siguiente

  localhost:8080/mutant/

el cuerpo del mensaje que debe ser enviado puede ser

 {
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}


En caso de ser exitosa la petición devuelve un status code 200, en caso contrario se devuelve 403.

Si se desea comprobar el segundo servicio se debe hacer una petición a la URL

  localhost:8080/stats/

Lo que da una respuesta como la siguiente

{
    "count_mutant_dna": 3.0,
    "count_human_dna": 2.0,
    "ratio": 1.0
}

En la clase MutantDetectorConfig se configura la conexión a la BD de firebase, se puede crear una nueva bd en firebase,
descargar la clave privada la cual es un archivo .json que debe ser colocado en la raíz del proyecto y posteriormente
reemplazada en la clase

        FileInputStream serviceAccount = new FileInputStream("./clave-privada.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        
        return FirestoreClient.getFirestore(firebaseApp);
