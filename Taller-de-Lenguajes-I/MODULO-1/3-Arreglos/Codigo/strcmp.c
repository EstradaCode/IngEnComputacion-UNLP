#include <stdio.h>
#include <string.h>
int main()
{
   char texto1[] = "Funci�n strcmp",
        texto2[] = "Preguntas?",
        texto3[] = "funci�n strcmp",
        texto4[] = "Esta terminado!";

   printf("%d %d %d\n",strcmp(texto1, texto2),
          strcmp(texto1, texto3),
          strcmp(texto1, texto4));

   return 0;
}

