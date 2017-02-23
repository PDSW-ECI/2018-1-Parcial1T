### Punto 1


El caso de la herramienta CAD, ya tiene implementado transformaciones, y las opciones de deshacer/rehacer.

Se quiere incorporar la funcionalidad de MACRO, existente en aplicacioens como Excel y Photoshop. 

Cuando se activa el macro, todas las acciones son grabadas, y podrán repetirse cuantas veces se quiera. Para esta versión, el macro NO tendrá en cuenta las acciones de deshacer. Es decir, que si durante la grabación de la macro se deshace una operación, el deshacer en sí mismo no será replicado en el macro.

Qué patrón es aplicable en este caso?, implemente las operaciones del controlador.

Bono: si se logra que se tenga en cuenta el deshacer y el rehacer dentro del MACRO.

Ejemplo: dibujar 20 figuras, grabar macro, dibujar otras 12, detener grabación. Al crear una figura, se debería poder replicar. 

