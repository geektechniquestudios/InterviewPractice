//given an int array, return an array of its items where only the values that are less than 5 are returned
const trimLargeItems = (inputArr) => inputArr.filter((i) => i < 5)
const arrayEquals = (a, b) => {
  return (
    Array.isArray(a) &&
    Array.isArray(b) &&
    a.length === b.length &&
    a.every((val, index) => val === b[index])
  )
}

const testArr = [1, 2, 3, 4, 5, 6, 7]
console.log(arrayEquals([1, 2, 3, 4, 5], trimLargeItems(testArr)))
