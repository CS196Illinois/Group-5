import json

f = open('genresForSorting.json', )

# returns JSON object as
# a dictionary
data = json.load(f)

def bubbleSort(arr, genreArr):
    n = len(arr)

    # Traverse through all array elements
    for i in range(n - 1):
        # range(n) also work but outer loop will repeat one time more than needed.

        # Last i elements are already in place
        for j in range(0, n - i - 1):

            # traverse the array from 0 to n-i-1
            # Swap if the element found is greater
            # than the next element
            if arr[j] < arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                genreArr[j], genreArr[j + 1] = genreArr[j + 1], genreArr[j]
    return genreArr

def searchForGenres(values):
    if len(values) != 3:
        return None
    max = 0
    pos = 0
    for i in range(0, 3):
        if (values[i] > max):
            max = values[i]
            pos = i
    print(pos)
    sortedGenres = []
    sortedGenresValues = []
    for i in data:
        positiveValues = i['positive values']
        negativeValues = i['negative values']
        if (positiveValues[pos] > 0):
            sortedGenres.append(i['name']);
            sum = 0
            for j in range(0, 3):
                sum += (values[j] * positiveValues[j])
                sum += (values[j] * negativeValues[j])
            sortedGenresValues.append(sum)
    j = 0
    for i in sortedGenres:
        print(i + ": " + str(sortedGenresValues[j]))
        j = j + 1
    sortedGenres = bubbleSort(sortedGenresValues, sortedGenres)
    print(sortedGenres)



searchForGenres([10, 10, 3])