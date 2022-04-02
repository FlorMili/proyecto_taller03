Feature:My List Creacion

Scenario: creation on my list

  Given que tengo abierto a my list app
  When yo registro una nueva lista
   |title | taller02 |
   |notes | Desarrollo |
  Then el listado "taller02" deberia ser creado


