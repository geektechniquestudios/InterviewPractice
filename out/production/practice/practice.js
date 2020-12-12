//flip a string
const invertString = (word) => {
  let left = 0
  let right = word.length - 1
  charArr = [...word]
  
  while (left < right) {
    swapValues(left, right, charArr)
    left++
    right--
  }
  return charArr
}

const swapValues = (left, right, charArr) => {
  let temp = charArr[left]
  charArr[left] = charArr[right]
  charArr[right] = temp
}

let result = invertString("something")
console.log(result)
