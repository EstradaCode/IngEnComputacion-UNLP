#include <stdio.h>
#include <string.h>
int main()
{
   char mensaje[50];

   sprintf(mensaje,
           "D�a de la independencia:%d/%d/%d",9,7,1816);

   printf("%s", mensaje);

   return 0;
}

