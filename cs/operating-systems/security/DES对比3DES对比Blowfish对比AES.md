## 1. 概述

在本教程中，我们将简要定义加密以及为什么我们首先需要它。之后，我们将比较一些加密算法，DES(数据加密标准)、3DES(三重DES)、AES(高级加密算法)和Blowfish在一些评估指标方面。

## 2. 什么是加密？

加密是一种保护信息安全的技术，因此只有授权方才能解释数据。发生的事情是，加密提供了对明文的转换，并将其转换为人类不可读的密文。

不仅在数字时代，密码在整个历史上也被广泛使用。在下图中，我们展示了密码分类的一般视图。正如我们所见，我们根据密码所具有的某些特征或根据其用途对密码进行分类。它们主要分为两种不同的类型；古典和现代课程。数字中最常见和使用的是现代类。[这是因为它的动态和静态加密技术为更详细的类、对称](https://www.baeldung.com/cs/hashing-vs-encryption#3-symmetric-encryption)和[非对称](https://www.baeldung.com/cs/hashing-vs-encryption#4-asymmetric-encryption)密码铺平了道路：

![密码](https://www.baeldung.com/wp-content/uploads/sites/4/2022/02/ciphers.png)

在[对称加密](https://www.baeldung.com/cs/symmetric-vs-asymmetric-cryptography)中，我们只有一个密钥来加密和解密数据，而在非对称加密中，我们使用两个不同的密钥。我们使用公钥进行加密，使用私钥进行解密。尽管公钥和私钥不同，但它们彼此相关，因此我们称它们为密钥对。DES、3DES、AES 和 Blowfish 是对称密钥块密码类的成员，我们将在整篇文章中描述它们并将它们相互比较。


## 3. 加密算法

让我们详细看看这些算法中的每一个。

### 3.1. 的

DES 由 IBM 开发，基于[Horst Feistel](https://en.wikipedia.org/wiki/Horst_Feistel)的设计。当它首次发布时，它是广泛使用和公开可用的密码系统之一。尽管它的首次亮相是在 70 年代，但后来被美国国家标准与技术研究院 (NIST) 采用。它是一种用于加密数字数据的对称密钥算法。

它的块大小为 64 位，并使用 Feistel 网络作为结构。它很慢并且没有在软件中使用。

它对密码学的进步产生了重大影响。但是，由于 56 位的短密钥长度，它对应用程序来说是不安全的。1999 年，[distributed.net](https://www.distributed.net/Main_Page)在 22 小时 15 分钟内破解了 DES 密钥。 在发生这些事件之后，NIST 撤回了该算法作为标准。3DES 是在 DES 漏洞之后出现的。

### 3.2. 3DES

三重 DES、3DES 或 TDES 正式是三重数据加密算法。它是一种对称密钥块密码，它对每个块应用 DES 算法三次。 它的块大小为 64 位，密钥长度为 112 或 168 位。它还使用 Feistel 网络，因为它基于 DES。

由于现代密码学技术和超级计算的发展，3DES 与 DES 一样也存在一些严重的漏洞。这就是为什么 NIST 在 2017 年对新应用程序以及到 2023 年对所有应用程序弃用 DES 和 3DES。

结果，AES 登场并取代了这些加密算法。

### 3.3. 河豚

Blowfish 是另一种对称密钥加密技术，由[Bruce Schneier](https://en.wikipedia.org/wiki/Bruce_Schneier)于 1993 年设计，作为 DES 加密算法的替代方案。因此，它比 DES 快得多，并提供良好的加密率。它的密钥长度为 446 位，比 DES 和 3DES 好得多。因此，破解Blowfish的密钥难度更大。它还具有 64 位的块大小。它也可以用在软件中。

然而，AES 今天受到更多关注，Schneier 推荐使用[Twofish](https://en.wikipedia.org/wiki/Twofish)作为 Blowfish 的替代品。它有免费许可证，可用于所有用途。

### 3.4. AES

AES 是另一种密码，可以保护数据免受恶意方的攻击。它是目前可用的最强大的加密算法之一。由于 AES 适当地结合了速度和安全性，它使我们能够不受任何干扰地继续我们的在线活动。

由于 AES 使用相同的密钥来加密和解密数据，因此它也是一种对称类型的加密。AES 加密密钥的长度有 128、192 和 256 位三种。每个密钥长度都有不同的可能的密钥组合。它具有与其他加密算法不同的结构，它使用替换置换网络。

虽然 AES 加密方法的密钥长度各不相同，但其块大小固定为 128 位或 16 字节。

## 4. DES 对比 3DES 对比 Blowfish 对比 AES

正如我们在前面几节中讨论的以及我们在下表中看到的那样，DES 不够安全。正是因为它的密钥长度短，暴力破解很容易破解。因此，尽管 3DES 是另一种选择，但它也不够安全：

![由 QuickLaTeX.com 呈现](https://www.baeldung.com/wp-content/ql-cache/quicklatex.com-484c73428eb0683e028b017cf19dde0d_l3.svg)

虽然 Blowfish 是不错的选择之一，但它的块大小不及 AES。我们将 AES 视为 DES 的继承者，作为组织的对称密钥加密算法。它接受不同的密钥大小，128、192 或 256 位。它在软件和硬件上都是高效的。

当我们查看以每秒 500 亿个密钥检查所有可能的密钥所需的时间时，我们可以看到 AES 的效率。即使它使用 128 位的密钥大小，它的结果也比其他加密算法好得多。

## 5. 其他一些评估指标

[在文献](https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=des+vs+3des+vs+blowfish+vs+aes&btnG=)中，有大量的研究评估和比较了一些加密算法。在这些研究中，他们通常会在比较过程中关注某些指标。实际上，这些指标主要包括加密和解密时间、内存使用情况，有时还包括熵、雪崩效应以及最佳编码所需的位数。

### 5.1. 加密时间

就是将明文转换成密文所需要的时间。加密过程的长度由密钥大小、明文块大小和模式决定。为了使系统快速响应，加密时间应该更少。

### 5.2. 解密时间

从密文中检索出明文所花费的时间。为了使系统快速，它应该不太像加密时间。

### 5.3. 内存使用情况

不同的加密算法需要不同数量的 RAM 来实现。内存使用取决于密钥大小、操作类型、操作数和初始化向量。


### 5.4. 雪崩效应

这是密码算法的理想特性。块密码和加密哈希函数，如果输入稍微改变，比如翻转一位，输出会发生很大变化。密钥或明文的这种微小变化必然导致高质量分组密码中密文的显着变化。虽然这个短语是由[Horst Feistel](https://en.wikipedia.org/wiki/Horst_Feistel)创造的，但这个概念可以追溯到[Shannon 的扩散](https://en.wikipedia.org/wiki/Claude_Shannon)。

## 六，总结

在本文中，我们简要介绍了有关 DES、3DES、Blowfish 和 AES 的一些信息。此外，我们还根据一些指标对它们进行了比较和评估。

尽管我们认为加密算法在理论上和实践上都可以保护数据，但请记住，存在各种攻击，例如恶意软件、密钥搜索、暴力破解、网络钓鱼、[侧信道](https://en.wikipedia.org/wiki/Side-channel_attack)等。这些攻击旨在找到一种破解系统的方法。

总而言之，虽然每种加密算法都有缺点和优点，但随着时间的推移，其中一些算法会失去可靠性。所以，我们在选择密码算法时要慎重。我们应该同时考虑我们的应用程序和加密算法的细节。