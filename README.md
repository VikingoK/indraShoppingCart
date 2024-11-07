# Carrito de Compras API

Este es el servicio de carrito de compras que permite agregar, actualizar, eliminar y consultar productos en el carrito. La API permite manejar los productos en el carrito de compras de un usuario de manera eficiente.


## Conexión a la Base de Datos

Este proyecto utiliza PostgreSQL como sistema de gestión de bases de datos. La configuración de la conexión se encuentra en el archivo `application.yml`

### Configuración de la Base de Datos

En el archivo `application.yml`, debes configurar los detalles de la base de datos:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres  # URL de la base de datos
    username: postgres                             # Nombre de usuario para la base de datos
    password: postgres                             # Contraseña de la base de datos
    driver-class-name: org.postgresql.Driver       # Driver de PostgreSQL

```

# script BD

-- public.in_products definition
-- DROP TABLE public.in_products;

CREATE TABLE public.in_products (
	id serial4 NOT NULL,
	"name" varchar NULL,
	price int4 NULL,
	CONSTRAINT in_products_pk PRIMARY KEY (id)
);
CREATE INDEX in_products_id_idx ON public.in_products USING btree (id);

INSERT INTO public.in_products
(id, "name", price)
VALUES(1, 'Nintendo switch', 100);

INSERT INTO public.in_products
(id, "name", price)
VALUES(2, 'Play station', 250);

-- public.in_shopping_cart definition
-- DROP TABLE public.in_shopping_cart;

CREATE TABLE public.in_shopping_cart (
	id serial4 NOT NULL,
	id_product int4 NOT NULL,
	create_day date NOT NULL,
	quantity int4 NOT NULL,
	CONSTRAINT in_shopping_cart_pk PRIMARY KEY (id)
);


-- public.in_shopping_cart foreign keys

ALTER TABLE public.in_shopping_cart ADD CONSTRAINT in_shopping_cart_in_products_fk FOREIGN KEY (id_product) REFERENCES public.in_products(id);