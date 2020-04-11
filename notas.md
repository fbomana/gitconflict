# Clonar el repositorio

    git clone xxxxxxxxxxxxxxxxxxxxx

# Lista de ramas no mergeadas
    git branch -r --no-merged origin/master

        Filtrar las que empiecen por fix, feature o epic.

## suponiendo que no haya ramas entre medias
    --> Obtenemos el commit raíz de la rama
        git merge-base  origin/master origin/feature/LNFDESAATLAS-4015 --> commit con la raíz de la rama

    --> Ficheros que han sido modificados entre el origen de la rama y su último commit
        git diff --name-only origin/feature/LNFDESAATLAS-4015 8a447b2fbcd64c10edec4f19659ec0932e5af0b5 

# Alternativa a usar git mediante línea de comandos: https://git-scm.com/book/uz/v2/Appendix-B%3A-Embedding-Git-in-your-Applications-JGit

# Crear tablas en BBDD en memoria:
    ramas
    ficheros
    ramas-ficheros

# Después de alimentarla:
    Buscar todas las entradas en ramas-ficheros agrupado por ficheros en los que la cuenta de ramas sea mayor de 1

    Conflicto: fichero + lista de ramas que la modifican.
    
    Listar conflictos ordenados por ramas / ficheros
