# flip a string
def invert_string(word):
    word = list(word)
    left, right = 0, len(word) - 1
    while left < right:
        word[left], word[right] = word[right], word[left]
        left+=1
        right-=1
    return "".join(word)

print(invert_string("invertThisDick"))

def invert_string(word):
    # convert to list
    # define left and right pointers
    # loop until pointers cross
        # swap values at indicies of pointers
        # move pointers inwards
    # convert arr back to string
    # return

# pseudocode
# talk about time complexity O(log(n)) and go over it
# write the code
# test the code