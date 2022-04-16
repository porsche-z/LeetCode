1,Overall thought:  
    -arrange all buildings' left points and right points in a priority queue(like sort all the x coordinates in an axis)  
    -Use a heap(PQ) to record the height of all the buildings in current position, but we cn only get the highest.(the feature of heap)  
    [details](https://leetcode-cn.com/problems/the-skyline-problem/solution/gong-shui-san-xie-sao-miao-xian-suan-fa-0z6xc/)  
    there is a similar [problem](https://sp19.datastructur.es/materials/clab/clab8/clab8)  
2.Some details.  
    -When constructing key points' queue,we have to obey   
        if both of the points are left key point, add the higher one first such as[[1,2,1],[1,2,3]]  
        if both of the points are right key point, remove the lower one first;  
if one of them is left, the other is right,add the left one(add the next building first)  
all of above are to avoid conflicts happening when some key points [coincidence](example.jpeg).
