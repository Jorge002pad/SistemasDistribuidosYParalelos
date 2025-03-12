import multiprocessing as mp
import random
import time

def bubble_sort(arr):
    start_time = time.time()
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    duration = time.time() - start_time
    return arr, duration

def quick_sort(arr):
    start_time = time.time()
    if len(arr) <= 1:
        return arr, 0
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    sorted_arr = quick_sort(left)[0] + middle + quick_sort(right)[0]
    duration = time.time() - start_time
    return sorted_arr, duration

def merge_sort(arr):
    start_time = time.time()
    if len(arr) <= 1:
        return arr, 0
    mid = len(arr) // 2
    left, _ = merge_sort(arr[:mid])
    right, _ = merge_sort(arr[mid:])
    sorted_arr = merge(left, right)
    duration = time.time() - start_time
    return sorted_arr, duration

def merge(left, right):
    result = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result.extend(left[i:])
    result.extend(right[j:])
    return result

def parallel_sorting(func, arr, output):
    sorted_arr, duration = func(arr)
    output.put((func.__name__, duration, sorted_arr))

if __name__ == "__main__":
    # Generar 2350 números aleatorios de hasta 5 dígitos
    data = [random.randint(0, 99999) for _ in range(2350)]
    print("Datos originales:", data, "\n")

    output = mp.Queue()

    # Crear procesos para cada algoritmo de ordenación
    processes = [
        mp.Process(target=parallel_sorting, args=(bubble_sort, data.copy(), output)),
        mp.Process(target=parallel_sorting, args=(quick_sort, data.copy(), output)),
        mp.Process(target=parallel_sorting, args=(merge_sort, data.copy(), output))
    ]

    # Iniciar los procesos
    for p in processes:
        p.start()
    
    # Esperar a que los procesos terminen
    for p in processes:
        p.join()
    
    # Obtener y mostrar los resultados
    while not output.empty():
        method, duration, sorted_data = output.get()
        print(f"\nMétodo: {method}\nTiempo de ejecución: {duration:.5f} segundos\nDatos ordenados: {sorted_data}\n")