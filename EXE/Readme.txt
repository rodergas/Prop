Compilar: javac -encoding "UTF-8" -classpath ".\LIBRERIAS\*"  Presentacion\*.java Persistencia\*.java Dominio\*.java
Crear jar : jar cf Youtube.jar Presentacion\*.class Persistencia\*.class Dominio\*.class *.gif *.jpg *.png *.pdf
Ejecutar: java -cp "Youtube.jar;LIBRERIAS\*" Presentacion.PruebaMain

Diferencia con Unix
Barras \ se reemplazan por /
; se reemplaza por :
