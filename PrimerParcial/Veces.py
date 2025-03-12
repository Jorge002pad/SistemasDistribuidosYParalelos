#Programa que genera numero aleatorios almacenados en una matriz y que
# hace uso de una función paralela que cuenta cuántos números están
# presentes, entre un # # rango, dado en cada fila
# Paralelizando usando Pool.map()
import multiprocessing as mp
import numpy as np
from time import time
# Obtención de los valores de manera aleatoria
np.random.RandomState(100)
arr = np.random.randint(0, 10, size=[200000, 5])
data = arr.tolist()
data[:5]
print("Valores aleatorios obtenidos")
print (data[:10])
print ("\n")
# Funcion
def howmany_within_range_rowonly(row, minimum=4, maximum=8):
    count = 0
    for n in row:
        if minimum <= n <= maximum:
            count = count + 1
    return count
pool = mp.Pool(mp.cpu_count())
results = pool.map(howmany_within_range_rowonly, [row for row in data])
print(results[:10])