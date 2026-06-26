# -_proyecto_Calculadora_de_39_Numeros_con_Java_Spring_Boot_MySQL_- :.
# Proyecto: Calculadora de 39 Números con Java + Spring Boot + MySQL:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/ff50dc9c-20c0-4d38-bf4d-6d9633592735" />  

```
  
## Descripcion:

Este proyecto desarrollado con **Java**, **Spring Boot**, **Spring Data JPA**, **Thymeleaf** y **MySQL** permite:

- ✅ Registrar una lista de **39 números**.
- ✅ Guardarlos en una base de datos **MySQL**.
- ✅ Calcular automáticamente la suma de los 39 números.
- ✅ Mostrar el resultado en una página web.

---

# Estructura del Proyecto

```text
Calculadora39Numeros
│
├── src
│   └── main
│       ├── java
│       │   ├── controlador
│       │   │      InicioController.java
│       │   │
│       │   ├── entidad
│       │   │      Numero.java
│       │   │
│       │   ├── repositorio
│       │   │      NumeroRepository.java
│       │   │
│       │   ├── servicio
│       │   │      NumeroService.java
│       │   │
│       │   └── CalculadoraApplication.java
│       │
│       └── resources
│            ├── application.properties
│            └── templates
│                 └── index.html
```

---

# 1. Base de Datos MySQL

```sql
CREATE DATABASE calculadora39;

USE calculadora39;

CREATE TABLE numeros (

    id INT AUTO_INCREMENT PRIMARY KEY,
    valor DOUBLE NOT NULL

);
```

---

# 2. Archivo `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/calculadora39
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

# 3. Entidad `Numero.java`

```java
package entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "numeros")
public class Numero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    public Numero() {
    }

    public Numero(Double valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
```

---

# 4. Repositorio `NumeroRepository.java`

```java
package repositorio;

import entidad.Numero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeroRepository extends JpaRepository<Numero, Integer> {

}
```

---

# 5. Servicio `NumeroService.java`

```java
package servicio;

import entidad.Numero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.NumeroRepository;

import java.util.List;

@Service
public class NumeroService {

    @Autowired
    private NumeroRepository repository;

    public void guardar(List<Numero> lista) {

        repository.deleteAll();

        repository.saveAll(lista);

    }

    public Double sumar() {

        return repository.findAll()
                .stream()
                .mapToDouble(Numero::getValor)
                .sum();

    }

}
```

---

# 6. Controlador `InicioController.java`

```java
package controlador;

import entidad.Numero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import servicio.NumeroService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private NumeroService service;

    @GetMapping("/")
    public String inicio() {

        return "index";

    }

    @PostMapping("/sumar")
    public String sumar(@RequestParam List<Double> numero,
                        Model model) {

        List<Numero> lista = new ArrayList<>();

        for (Double n : numero) {

            lista.add(new Numero(n));

        }

        service.guardar(lista);

        Double suma = service.sumar();

        model.addAttribute("resultado", suma);

        return "index";

    }

}
```

---

# 7. Vista `index.html`

```html
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>

<meta charset="UTF-8">

<title>Sumar 39 números</title>

<style>

input{

width:80px;
margin:4px;

}

</style>

</head>

<body>

<h2>Ingrese los 39 números</h2>

<form action="/sumar" method="post">

<div>

<script>

</script>

</div>

<div id="campos"></div>

<br>

<button type="submit">

Calcular Suma

</button>

</form>

<h2 th:if="${resultado!=null}">

Resultado:

<span th:text="${resultado}"></span>

</h2>

<script>

let div=document.getElementById("campos");

for(let i=1;i<=39;i++){

div.innerHTML+=
"Número "+i+
": <input type='number' step='any' name='numero' required><br>";

}

</script>

</body>

</html>
```

---

# Funcionamiento

Al ejecutar la aplicación:

```
http://localhost:8080
```

Aparecerán automáticamente **39 cajas de texto**.

## Ejemplo

```text
10
20
30
15
18
...
39 números
```

Al presionar el botón **Calcular Suma**, la aplicación realiza automáticamente las siguientes operaciones:

1. Guarda los 39 números en la base de datos MySQL.
2. Lee nuevamente los registros almacenados.
3. Calcula la suma de todos los números.
4. Muestra el resultado en pantalla.

---

# Ejemplo de Entrada

```text
1
2
3
4
5
...
39
```

## Resultado

```text
780
```

---

# Tecnologías Utilizadas

- Java 17 o superior
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL
- Maven
- HTML
- JavaScript

---

# Flujo del Proyecto

```text
Usuario
    │
    ▼
Formulario HTML
    │
    ▼
InicioController
    │
    ▼
NumeroService
    │
    ▼
NumeroRepository
    │
    ▼
MySQL
    │
    ▼
Consulta de Datos
    │
    ▼
Cálculo de la Suma
    │
    ▼
Resultado en la Página Web
```

---

# Resultado Esperado

La aplicación permitirá:

- Registrar exactamente **39 números**.
- Almacenar los datos en MySQL.
- Recuperar los datos almacenados.
- Calcular automáticamente la suma.
- Mostrar el resultado mediante Thymeleaf.

---

# Mejoras Recomendadas

El proyecto puede ampliarse para incluir las siguientes funcionalidades:

- ✅ Calcular la suma.
- ✅ Calcular el promedio de los 39 números.
- ✅ Mostrar el número mayor.
- ✅ Mostrar el número menor.
- ✅ Mostrar la cantidad de números ingresados.
- ✅ Registrar un historial de operaciones con fecha y hora.
- ✅ Exportar resultados a PDF.
- ✅ Exportar resultados a Excel.
- ✅ Agregar autenticación de usuarios con Spring Security.
- ✅ Incorporar Bootstrap para mejorar la interfaz gráfica.
- ✅ Crear una API REST para consultar las operaciones realizadas.

---

# Conclusión

Este proyecto constituye un ejemplo práctico del uso conjunto de:

- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL

El desarrollo demuestra cómo integrar un formulario web con una base de datos relacional para almacenar información, procesarla y presentar resultados dinámicos al usuario. Además, sirve como base para proyectos académicos y profesionales donde se requiera la captura, persistencia y procesamiento de datos numéricos.
