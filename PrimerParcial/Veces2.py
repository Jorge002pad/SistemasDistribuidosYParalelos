import multiprocessing as mp
import numpy as np
from time import time

# Obtención de los datos aleatorios
np.random.RandomState(100)
arr = np.random.randint(0, 10, size=[200000, 5])
data = arr.tolist()
data[:5]
print("Valores aleatorios obtenidos")
print (data[:10])
print ("\n")
#Funcion que obtiene la cantidad de valores que se encuentran en el rando 4 a 8
def howmany_within_range(row, minimum=4, maximum=8):
    count = 0
    for n in row:
        if minimum <= n <= maximum:
            count = count + 1
    return count
pool = mp.Pool(mp.cpu_count())
results = pool.starmap(howmany_within_range, [(row, 4, 8) for row in data])
pool.close()
print(results[:10])