#include <stdio.h>
void UpperCase(char *);
int main()
{   char string[] = "caracteres";
    printf("El string antes de la conversion"
           " es : %s\n",string);
    UpperCase(string);
    printf("El string despues de la conversion"
           "es : %s\n", string);

    return 0;
}
void UpperCase(char *s)
{   while (*s != '\0')
    { /* no encontr� el fin del string */
      if (*s >= 'a' && *s <= 'z')
         *s -= 32;  /* convierte el ASCII a may�scula */
      ++s;  /* s apunta al pr�ximo caracter */
    }
}
