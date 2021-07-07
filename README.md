# ProyectoBancoEstatal

# Descripción
La compañía "Banco Estatal" S.A encargó un sistema para gestionar todos sus casos de fraudes bancarios este pudiendo funcionar tanto en web como móvil, esta aplicación está pensada para el uso de personal de banco y de clientes.
Para todos los clientes este podrá levantar una solicitud de fraude para sus tarjetas iniciando sesión con su cuenta. De no tener una cuenta la página dará la opción de registrarse.
Mientras que los ejecutivos podrán ver y solucionar los casos de ser necesario. También contaran con la posibilidad de generar un PDF con todos los casos vistos por él, al hacerlo el cliente recibirá un correo.

# Instalación
Este proyecto usa una API Spring que tomara uso de las vistas para los casos web y móvil.
En la carpeta "ProyectoFraude" se encuentra la API, para trabajar con la API se debe abrir esta carpeta como un proyecto en su IDE.
Dado que esta api funciona con una base de datos PSQL "postgress" tendra que generar las tablas en su usuario. Estas tablas siendo:

Bank_database

    client
      (rut
      fullName
      address
      mail
      accountNumber)
    manager
      (rut
      fullName)
    ticket
      (id
      cardType
      comment)
    users
      (rut
      password)
Una vez deberá modificar los datos de:
"proyectoBancoEstatal/ProyectoFraude/src/main/resources/application.properties"
Dentro del archivo se podrán ver los datos que deberá modificar.

# Usos

# 
