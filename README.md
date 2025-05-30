# Server Threading Models

This repository demonstrates three different server-side threading approaches in Java:

- **SingleThreaded**: Basic server handling one client at a time.
- **MultiThreaded**: Creates a new thread for each incoming connection.
- **ThreadPool**: Uses a fixed thread pool to manage concurrent connections efficiently.

---

## 🧪 Performance Testing (JMeter)

I tested all three implementations under heavy load using **Apache JMeter** with **600,000 requests in 1 minute**.

| Model          | Throughput (req/min) | Threads Accepted | Crashes | Deviation |
|----------------|----------------------|------------------|---------|-----------|
| SingleThreaded | 128                  | ~18,549          | ✅ Yes  | N/A       |
| MultiThreaded  | 387,920              | ✅ All           | ❌ No   | ±59       |
| ThreadPool     | 831,869              | ✅ All           | ❌ No   | ±4        |

---

## 🛠️ Technologies Used

- Java (Socket Programming)
- Apache JMeter (Load Testing)
- ExecutorService for thread pooling

---

## 📂 Structure

```
.
├── MultiThreaded
├── SingleThreaded
└── ThreadPool
```

Each folder contains a Java implementation of the corresponding server model.

---

## 📌 Conclusion

- **Single-threaded servers** are simple but highly inefficient under load.
- **Multi-threaded servers** scale better but may exhaust system resources.
- **Thread pooling** offers the best performance and stability with minimal deviation.
