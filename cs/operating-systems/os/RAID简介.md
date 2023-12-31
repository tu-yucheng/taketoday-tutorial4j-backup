## 1. 概述

磁盘存储的性能和可靠性对任何组织都是至关重要的。独立磁盘冗余阵列 (RAID) 是一项有助于在计算机系统中实现高性能和更高可靠性的功能。

在本教程中，我们将讨论 RAID 作为一种技术及其各种功能。

## 2.什么是RAID？

RAID 系统由两个或多个并行工作的磁盘驱动器组成。这些磁盘驱动器可以是硬盘或固态驱动器 (SSD)。RAID 提供了多个级别，每个级别都提供一定程度的性能和可靠性。

但是，这些 RAID 级别并未标准化。组织使用数据关键性的性质来决定 RAID 级别。

## 3.RAID级别

RAID根据性能和可靠性参数分为不同的级别。以下是重要的 RAID 级别：

### 3.1. 0 级 – 剥离

![RAID-RAID 0](https://www.baeldung.com/wp-content/uploads/sites/4/2020/07/RAID-RAID-0-300x234-1.png)

在 RAID 0 系统中，数据被分成块。这些块同时写入所有驱动器。通过同时使用多个磁盘(至少两个)，可以提供更好的 I/O 性能。此外，它使用所有磁盘的存储，并且由于其简单性而易于实现。

但是，RAID 0 不是容错的。由于数据分布在多个驱动器上，当单个驱动器出现故障时，系统中的所有数据都将由于其碎片化特性而丢失。

### 3.2. 级别 1 – 镜像

![RAID-RAID 1](https://www.baeldung.com/wp-content/uploads/sites/4/2020/07/RAID-RAID-1-300x234-1.png)

在 RAID 级别 1 系统中，数据被到另一个磁盘驱动器中。因此，它也称为镜像。

由于数据在附加磁盘驱动器中可用，因此该系统提供了出色的读取速度。它还提供更好的容错能力，因为有数据备份。因此，如果一个磁盘驱动器出现故障，则使用镜像磁盘驱动器。

然而，RAID 1 的最大缺点是它需要两倍的资源。RAID 1 适用于关键任务存储，例如数据安全至关重要的会计系统。

### 3.3. 级别 5 – 带奇偶校验的剥离

![RAID-RAID 5](https://www.baeldung.com/wp-content/uploads/sites/4/2020/07/RAID-RAID-5-300x173-1.png)

RAID 5 是最常用和最安全的 RAID 级别。它至少需要三个磁盘，但最多可以使用 16 个。

在 RAID 5 中，数据块跨驱动器条带化，并且在一个驱动器上写入所有块数据的奇偶校验和。奇偶校验是通过对来自驱动器 1 的位与来自驱动器 2 的位执行异或运算并将结果存储在驱动器 3 上计算的。此奇偶校验数据未写入固定驱动器，它们分布在所有驱动器中。

使用奇偶校验数据，如果数据不再可用，计算机可以重新计算其他数据块之一的数据。这意味着RAID 5 阵列可以承受单个驱动器故障而不会丢失数据。

RAID 级别 5的主要优点是它平衡了性能和可靠性。

### 3.4. 级别 6 – 带双重奇偶校验的剥离

![RAID-RAID 6](https://www.baeldung.com/wp-content/uploads/sites/4/2020/07/RAID-RAID-6-300x173-1.png)

RAID 6 与 RAID 5 类似，只有一个例外。在 RAID 6 中，奇偶校验数据写入两个磁盘。这意味着我们至少需要四个磁盘来配置 RAID 级别 6。此外，RAID 级别 6 系统可以同时管理两个驱动器的故障。

尽管 RAID 6 系统比其他系统更安全，但它也需要付出代价。由于额外的奇偶校验数据，写入操作要慢得多。由于额外的容错级别，RAID 6 优于 RAID 5。这是一个全方位的系统，结合了高效的存储与出色的安全性和良好的性能。

### 3.5. 级别 10 – RAID 1 和 RAID 0 的组合

![RAID-RAID10](https://www.baeldung.com/wp-content/uploads/sites/4/2020/07/RAID-RAID10-300x207-1.png)

这种方法在单个系统中结合了 RAID 0 和 RAID 1 的优点。这种混合 RAID 配置通过在辅助驱动器上镜像所有数据来提供安全性，同时在每组驱动器上使用条带化来加速数据传输。

RAID 10 的主要优点是由于数据镜像，故障恢复率很高。但是，与使用 RAID 0 时的情况一样，由于镜像，我们损失了一半的存储容量。

## 4. RAID 与传统数据备份

尽管 RAID 提供了针对磁盘故障的可靠性，但它不能替代传统的磁盘备份。RAID 1 系统可以承受一个磁盘故障，而 RAID 6 系统甚至可以同时承受两个磁盘故障。但是，为了防止数据丢失，我们仍然需要从 RAID 系统进行备份，原因如下：

-   如果数据在所有磁盘上丢失，备份让我们恢复数据
-   我们可以将备份保存在场外位置，并在需要时使用它们来恢复数据

## 5.总结

在本教程中，我们讨论了 RAID 及其在系统性能和数据可靠性方面的重要性。

然后我们讨论了各种 RAID 级别及其优点和缺点。我们还探讨了每个 RAID 级别如何帮助提高数据可靠性和 I/O 性能。

最后，我们讨论了使用 RAID 和数据备份的必要性。