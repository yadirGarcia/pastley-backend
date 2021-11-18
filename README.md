<p align="center">
    <a href="https://github.com/DeveUp/pastley-backend" target="_blank"> 
        <img src="https://raw.githubusercontent.com/DeveUp/pastley-backend/c830c425e9060de4d66f044b717647f32a0a262b/pastley-logos/04.svg" alt="docker" width="400" height="200"/> 
    </a> 
</p> 
    
# Pastley

Sistema ERP que permite gestionar una pastelería automatización en las ventas de los productos y la centralización de los mismos en un catálogo virtual que esté disponible las 24 horas para todo el público y que desde ahí mismo se puedan realizar pedidos con esto se logra reducir los tiempos de ventas y tener una mayor confiabilidad.

## Documentation

[Documentation](https://drive.google.com/file/d/1z73tLfO8bVCXkmsOkv2Q_f2uBGTyNW_P/view?usp=sharing)

## Screenshots

![App Screenshot](https://i.ibb.co/g4XXPZN/Captura-de-pantalla-de-2021-11-17-19-05-57.png)
![App Screenshot](https://i.ibb.co/W6rF6kX/Captura-de-pantalla-de-2021-11-17-19-06-27.png)


## Arquitectura de Implementacion

![App Arquitectura](https://i.ibb.co/Tgj2cBf/Copia-de-Arquitectura-Arquitetura-de-implementacion-drawio.png)

## Descripcion Microservicios

![App Arquitectura](https://i.ibb.co/SN0DXfH/Copia-de-Arquitectura-Descripci-n-detallada-microservicio-drawio.png)


## Características

### Autenticacion

### Gestion de Usuario

  #### Administrador: 
  En el se refleja la información presupuesto, totalidad de productos, totalidad de clientes y ventas
realizadas, así como también gestionar su información personal, información de la empresa y
productos de la empresa,así como gestionar los demas perfiles de sistemas
  
  #### Cajero: 
  También puede gestionar su información, además, otra de sus funciones es poder
mantener una comunicación con los diferentes usuarios sobre alguna queja, recomendación,
felicitación,entre otras, también podrá observar la información de los diferentes clientes y por
último registrar los productos.

  #### Cliente:
  Es aquel que va utilizar el sistema para realizar compras de los diferentes
productos que se encuentren en la plataforma, sean tortas, postres, panes, bebidas.


### Gestión de productos

Se manejará la creación de categorías, principalmente se tiene como
referencia: (tortas, postres, bebidas).

De igual manera también es la creación de los diferentes productos pertenecientes a la
categoría designada, estos productos contarán con especificaciones como nombre, precio,
descripción, ingredientes y entre otras de ser necesario a medida que se esté avanzando en el
desarrollo del microservicio.


### Gestión de ventas

Es el encargado de realizar el manejo de las ventas y pagos de los diferentes
productos seleccionados por el cliente, estos productos escogidos por el usuario se cargaran en
el carrito de compras y siguiendo el proceso de compra podremos finalizar la venta de los
productos por el método de pago de paypal.


### Contacto
Es el que va a manejar la comunicación del usuario con la empresa, el
usuario podrá contactar con la empresa por medio de un formulario donde podremos plantear
nuestra (queja, reclamo o petición), esto se registrará en la base de datos de la empresa la
petición y se re enviará al correo electrónico del usuario el mensaje que realizó.



## Color Reference

| Color             | Hex                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Example Color | ![#F0A500](https://via.placeholder.com/10/F0A500?text=+) #F0A500 |
| Example Color | ![#334756](https://via.placeholder.com/10/334756?text=+) #334756 |
| Example Color | ![#082032](https://via.placeholder.com/10/082032?text=+) #082032 |
| Example Color | ![#000000](https://via.placeholder.com/10/000000?text=+) #000000 |


## Lenguajes y Herramientas Programación


![Logo](https://i.ibb.co/Yfqc7JK/Project-Lombok-1.png)

## Installation

Install my-project with npm

```bash
  npm install my-project
  cd my-project
```
  

## Actores

- [@SerBuitrago](https://github.com/SerBuitrago)
- [@leynerjoseoa](https://github.com/leynerjoseoa)


## Contribuyendo

- [@yadirGarcia](https://github.com/yadirGarcia)
- [@soleimygomez](https://github.com/soleimygomez)
- [@jhonatanbeltran](https://github.com/jhonatanbeltran)

¡Las contribuciones son siempre bienvenidas!


## Agradecimientos

 - [TODO SE LO DEBEMOS A YADIR QUE HIZO TODA LA APLICACION](https://web.facebook.com/kajarka35/)




## Estado

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/tterb/atomic-design-ui/blob/master/LICENSEs)

