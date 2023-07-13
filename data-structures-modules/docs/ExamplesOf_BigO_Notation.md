## 1. 概述

在本文中，我们将讨论大O表示法的含义。我们将通过几个示例来研究它对代码运行时间的影响。

## 2. 大O符号的含义

我们经常听到使用大O符号描述的算法的性能。

算法性能(或算法复杂性)的研究属于算法分析领域。算法分析表明了算法消耗了多少资源(例如磁盘空间或时间)的问题。

我们把时间视为一种资源。通常，算法完成所需的时间越短越好。

## 3. 恒定时间算法 - O(1)

算法的输入规模大小如何影响其运行时间？理解大O的关键是理解事物的增长速率。这里讨论的速率是每个输入大小所花费的时间。

考虑以下简单代码：

```
int n = 1000;
System.out.println("Hey - your input is: " + n);
```

显然，上面n是什么并不重要。这段代码需要固定的运行时间。它不依赖于n的大小。

类似地：

```
int n = 1000;
System.out.println("Hey - your input is: " + n);
System.out.println("Hmm.. I'm doing more stuff with: " + n);
System.out.println("And more: " + n);
```

上面的例子也是常数时间。即使运行时间是原来的3倍，它也不取决于输入的大小n。我们将恒定时间算法表示如下：O(1)。请注意，O(2)、O(3)甚至O(1000)都表示相同的意思。

我们并不关心它到底需要多长时间，只关心它需要恒定的时间。

## 4. 对数时间算法 - O(log n)

恒定时间算法(渐近地)是最快的。对数时间是第二快的。不幸的是，它们有点难以理解。

对数时间算法的一个常见示例是二分搜索算法。

这里重要的是运行时间与输入的对数成比例增长(在这种情况下，以2为底的对数)：

```
for (int i = 1; i < n; i = i  2){
  System.out.println("Hey - I'm busy looking at: " + i);
}
```

如果n为8，则输出如下：

```
Hey - I'm busy looking at: 1
Hey - I'm busy looking at: 2
Hey - I'm busy looking at: 4
```

我们的简单算法运行log(8) = 3次。

## 5. 线性时间算法 - O(n)

在对数时间算法之后，下一个最快的为：线性时间算法。

如果我们说某些东西线性增长，我们的意思是它的增长与其输入的大小成正比。

考虑一个简单的for循环：

```
for (int i = 0; i < n; i++) {
  System.out.println("Hey - I'm busy looking at: " + i);
}
```

这个for循环运行了多少次？当然是n次了。我们不知道这需要多长时间才能运行，我们也不担心这一点。

我们所知道的是，上面介绍的简单算法将随着其输入的大小线性增长。

我们更喜欢0.1n的运行时间而不是(1000n + 1000)，但两者仍然是线性算法；它们都与输入的规模成正比增长。

同样，如果算法更改为以下内容：

```
for (int i = 0; i < n; i++) {
  System.out.println("Hey - I'm busy looking at: " + i);
  System.out.println("Hmm.. Let's have another look at: " + i);
  System.out.println("And another: " + i);
}
```

运行时的输入大小n仍然是线性的。我们将线性算法表示如下：O(n)。

与恒定时间算法一样，我们不关心运行时的细节。O(2n+1)与O(n)相同，因为大O表示法关注的是输入规模的增长。

## 6. N Log N时间算法 - O(n log n)

n log n是下一类算法。运行时间与输入的n log n成比例增长：

```
for (int i = 1; i <= n; i++){
  for(int j = 1; j < n; j = j  2) {
    System.out.println("Hey - I'm busy looking at: " + i + " and " + j);
  }
}
```

例如，如果n为8，则此算法将运行8xlog(8)=8x3=24次。对于大O表示法来说，for循环中是否有严格的不等式是无关紧要的。

## 7. 多项式时间算法 - O(n<sup>p</sup>)

接下来是多项式时间算法。该类算法甚至比n log n算法还要慢。

多项式是一个通用术语，包含二次(n<sup>2</sup>)、三次(n<sup>3</sup>)、四次(n<sup>4</sup>)等函数。
重要的是要知道O(n<sup>2</sup>)比O(n<sup>3</sup>)快，O(n<sup>3</sup>)比O(n<sup>4</sup>)快，等等。

让我们看一个二次时间算法的简单示例：

```
for (int i = 1; i <= n; i++) {
  for(int j = 1; j <= n; j++) {
    System.out.println("Hey - I'm busy looking at: " + i + " and " + j);
  }
}
```

此算法将运行8<sup>2</sup>=64次。注意，如果我们继续嵌套另一个for循环，这将成为一个O(n<sup>3</sup>)算法。

## 8. 指数时间算法 - O(k<sup>n</sup>)

现在我们正在进入危险的领域；这些算法与输入大小取幂的某个因子成比例增长。

例如，O(2<sup>n</sup>)算法每增加一个输入就会翻倍。因此，如果n=2，这些算法将运行4次；如果n=3，它们将运行8次(有点像反过来的对数时间算法)。

每增加一个输入，O(3<sup>n</sup>)算法就会增加三倍，每增加一个输入，O(k<sup>n</sup>)算法就会增加k倍。

让我们看一个O(2<sup>n</sup>)时间算法的简单示例：

```
for (int i = 1; i <= Math.pow(2, n); i++){
  System.out.println("Hey - I'm busy looking at: " + i);
}
```

此算法将运行2<sup>8</sup>=256次。

## 9. 阶乘时间算法 - O(n!)

在大多数情况下，这是非常糟糕的。这类算法的运行时间与输入大小的阶乘成正比。

一个典型的例子是使用暴力方法解决旅行商问题。

让我们看一个简单的O(n!)算法，如前几节所述：

```
for (int i = 1; i <= factorial(n); i++){
  System.out.println("Hey - I'm busy looking at: " + i);
}
```

其中factorial(n)仅计算n!(n的阶乘)。如果n为8，此算法将运行8!=40320次。

## 10 渐近函数

大O就是所谓的渐近函数。所有这一切意味着，它关注的是算法在极限时的性能。即-当大量输入被抛出时。

大O不关心你的算法在处理小规模输入时的表现。它涉及到大量的输入(想想排序一个100万个数字的列表，而不是排序一个5个数字的列表)。

另一件需要注意的是，还有其他渐近函数。Big Θ(theta)和Big Ω(omega)也都描述了极限的算法(记住，这只意味着巨大输入的极限)。

要了解这3个重要函数之间的区别，我们首先需要知道Big O、Big Θ和Big Ω中的每一个都描述了一个集合(即元素的集合)。

在这里，我们集合的成员是算法本身：

+ 大O描述了运行速度不低于一定速度的所有算法集(这是一个上限)
+ 相反，大Ω描述了运行速度不超过某一特定速度的所有算法集(这是一个下限)
+ 最后，大Θ描述了以一定速度运行的所有算法集(就像等式一样)

我们上面给出的定义在数学上并不准确，但它们有助于我们的理解。

通常，你会听到用大O来描述问题，但了解大Θ和大Ω并没有坏处。

## 11. 总结

在本文中，我们介绍了大O表示法，以及理解算法的复杂度如何影响代码的运行时间。