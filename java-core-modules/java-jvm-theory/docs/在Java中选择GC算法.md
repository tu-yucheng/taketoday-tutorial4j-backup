## 1. 概述

JVM 附带了各种垃圾收集选项以支持各种部署选项。这样，我们就可以灵活地选择要为我们的应用程序使用的[垃圾收集器。](https://www.baeldung.com/jvm-garbage-collectors)

默认情况下，JVM 根据主机的类别选择最合适的垃圾收集器。然而，有时，我们的应用程序会遇到与 GC 相关的主要瓶颈，这要求我们更好地控制使用哪种算法。问题是，“人们如何确定 GC 算法？”

在这篇文章中，我们试图回答这个问题。

## 2.什么是GC？

Java 是一种[垃圾收集语言](https://www.baeldung.com/java-memory-management-interview-questions)，我们无需为应用程序手动分配和释放内存。操作系统分配给 JVM 进程的整个内存块称为堆。然后 JVM 将这个堆分成两组，称为代。这种分解使它能够应用各种技术来进行有效的内存管理。

新生代(伊甸园)是分配新创建的对象的地方。它通常很小(100-500MB)并且还有两个幸存者空间。老年代是存储旧对象或老化对象的地方——这些对象通常是长期存在的对象。这个空间比年轻一代要大得多。

收集器不断跟踪年轻一代的丰满度并触发次要收集，在此期间，活动对象将移动到幸存者空间之一，而死亡对象将被移除。如果一个对象在一定数量的次要 GC 中幸存下来，收集器会将其移动到老年代。当旧空间被认为已满时，将发生主要 GC，并将死对象从旧空间中移除。

在这些 GC 的每一个期间，都有停止世界的阶段，在此期间没有其他任何事情发生——应用程序无法为任何请求提供服务。我们称之为暂停时间。

## 3. 需要考虑的变量

就像 GC 使我们免受手动内存管理的影响一样，它也是有代价的。我们的目标应该是使 GC 运行时开销尽可能低。有几个变量可以帮助我们决定哪个收集器最能满足我们的应用程序需求。我们将在本节的其余部分讨论它们。

### 3.1. 堆大小

这是操作系统分配给 JVM 的工作内存总量。理论上，内存越大，回收前可以保留的对象越多，导致GC时间越长。可以使用-Xms=<n>和-Xmx=<m>命令行选项设置最小和最大堆大小。

### 3.2. 应用程序数据集大小

这是应用程序需要保留在内存中才能有效工作的对象的总大小。由于所有新对象都加载到新生代空间中，这肯定会影响最大堆大小，从而影响 GC 时间。

### 3.3. CPU 数量

这是机器可用的内核数。这个变量直接影响我们选择哪种算法。有些仅在有多个可用内核时才有效，而其他算法则相反。

### 3.4. 暂停时间

暂停时间是垃圾收集器停止应用程序回收内存的持续时间。 此变量直接影响延迟，因此目标是限制这些暂停中的最长暂停时间。

### 3.5. 吞吐量

这里，我们指的是进程实际花费在应用程序工作上的时间。应用程序时间与执行 GC 工作所花费的开销时间越长，应用程序的吞吐量就越高。

### 3.6. 内存占用

这是 GC 进程使用的工作内存。当设置的内存有限或进程较多时，此变量可能决定可伸缩性。

### 3.7. 迅速

这是从对象死亡到它占用的内存被回收之间的时间。它与堆大小有关。理论上，堆大小越大，及时性越低，因为触发收集的时间越长。

### 3.8. Java版

随着新 Java 版本的出现，支持的 GC 算法和默认收集器通常会发生变化。我们建议从默认收集器及其默认参数开始。根据所选的收集器，调整每个参数会产生不同的效果。

### 3.9. 潜伏

这是应用程序的响应能力。GC 暂停直接影响这个变量。

## 4.垃圾收集器

除了串行 GC，当有多个核心可用时，所有其他收集器都是最有效的：

### 4.1. 串行GC

串行收集器使用单个线程来执行所有垃圾收集工作。它在某些小型硬件和操作系统配置上默认选中，或者可以使用选项-XX:+UseSerialGC显式启用。

优点：

-   没有线程间通信开销，效率比较高。
-   它适用于客户端机器和嵌入式系统。
-   它适用于具有小数据集的应用程序。
-   即使在多处理器硬件上，如果数据集很小(最多 100 MB)，它仍然是最有效的。

缺点：

-   对于具有大型数据集的应用程序来说效率不高。
-   它不能利用多处理器硬件。

### 4.2. 并行/吞吐量GC

这个收集器使用多线程来加速垃圾收集。在 Java 版本 8 及更早版本中，它是服务器类机器的默认设置。我们可以使用-XX:+UseParallelGC选项覆盖这个默认值。

优点：

-   它可以利用多处理器硬件。
-   对于更大的数据集，它比串行 GC 更有效。
-   它提供了高整体吞吐量。
-   它试图最小化内存占用。

缺点：

-   应用程序在 stop-the-world 操作期间会产生很长的暂停时间。
-   它不能很好地适应堆大小。

如果我们想要更高的吞吐量并且不关心暂停时间是最好的，就像批处理任务、离线作业和 Web 服务器等非交互式应用程序的情况一样。

### 4.3. 并发标记清除 (CMS) GC

我们认为 CMS 是一个主要并发的收集器。这意味着它与应用程序同时执行一些昂贵的工作。它旨在通过消除与并行和串行收集器的完整 GC 相关的长时间暂停来实现低延迟。

我们可以使用选项-XX:+UseConcMarkSweepGC 来启用 CMS 收集器。核心 Java 团队从 Java 9 开始弃用它，并在 Java 14 中将其完全删除。

优点：

-   它非常适合低延迟应用程序，因为它最大限度地减少了暂停时间。
-   它与堆大小的缩放相对较好。
-   它可以利用多处理器机器。

缺点：

-   它从 Java 9 开始被弃用，并在 Java 14 中被删除。
-   当数据集达到巨大的规模或收集巨大的堆时，它变得相对低效。
-   它要求应用程序在并发阶段与 GC 共享资源。
-   可能存在吞吐量问题，因为在 GC 操作上花费了更多时间。
-   总的来说，由于它主要是并发的性质，它使用了更多的 CPU 时间。

### 4.4. G1(垃圾优先)GC

G1 和 CMS 一样使用多个后台 GC 线程扫描和清理堆。实际上，核心 Java 团队将 G1 设计为对 CMS 的改进，用额外的策略修补了它的一些弱点。

除了增量和并发收集之外，它还跟踪以前的应用程序行为和 GC 暂停以实现可预测性。然后，它首先专注于回收最有效区域的空间——那些大部分充满垃圾的区域。出于这个原因，我们称之为垃圾优先。

从 Java 9 开始，G1 是服务器级机器的默认收集器。我们可以通过在命令行上提供-XX:+UseG1GC来显式启用它。

优点：

-   它对巨大的数据集非常有效。
-   它充分利用了多处理器机器。
-   它是实现暂停时间目标的最有效方法。

缺点：

-   当有严格的吞吐量目标时，它不是最好的。
-   它要求应用程序在并发收集期间与 GC 共享资源。

G1 最适合具有非常严格的暂停时间目标和适度总吞吐量的应用程序，例如交易平台或交互式图形程序等实时应用程序。

### 4.5. Z 垃圾收集器 (ZGC)

ZGC 是一个可扩展的低延迟垃圾收集器。它设法在甚至数 TB 的堆上保持低暂停时间。它使用的技术包括参考着色、重定位、负载屏障和重新映射。它非常适合服务器应用程序，其中大堆很常见并且需要快速的应用程序响应时间。

它作为实验性 GC 实现在 Java 11 中引入。我们可以通过在命令行上提供-XX:+UnlockExperimentalVMOptions -XX:+UseZGC来显式启用它。有关更详细的说明，请访问[我们关于 Z 垃圾收集器的文章。](https://www.baeldung.com/jvm-zgc-garbage-collector)

## 5.总结

对于许多应用程序，收集器的选择从来都不是问题，因为JVM 默认值通常就足够了。这意味着应用程序可以在垃圾收集的情况下以可接受的频率和持续时间暂停执行良好。然而，对于一大类应用程序来说，情况并非如此，尤其是那些具有庞大数据集、许多线程和高事务率的应用程序。

在本文中，我们探讨了 JVM 支持的垃圾收集器。我们还查看了可帮助我们根据应用程序需求选择正确收集器的关键变量。