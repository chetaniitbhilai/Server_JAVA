# Server Threading Models

This repository demonstrates three different server-side threading approaches in Java:

- **SingleThreaded**: Basic server handling one client at a time.  
- **MultiThreaded**: Creates a new thread for each incoming connection.  
- **ThreadPool**: Uses a fixed thread pool to manage concurrent connections efficiently.  

---

## 🧪 Performance Testing (JMeter)

Tested under heavy load using **Apache JMeter** with **600,000 requests in 1 minute**.

### 🚀 Basic Handling (No File I/O)

| Model          | Throughput (req/min) | Threads Accepted | Crashes | Deviation |
|----------------|----------------------|------------------|---------|-----------|
| SingleThreaded | 128                  | ~18,549          | ✅ Yes  | N/A       |
| MultiThreaded  | 387,920              | ✅ All           | ❌ No   | ±59       |
| ThreadPool     | 831,869              | ✅ All           | ❌ No   | ±4        |

### 📄 With File Reading

| Model          | Throughput (req/min) | Deviation | Notes               |
|----------------|----------------------|-----------|---------------------|
| SingleThreaded | 4,529                | ±17,551   | Sent 18,605         |
| MultiThreaded  | 660,432              | ±57       | Missed 85 requests  |
| ThreadPool     | 743,617              | ±3        | ✅ All threads ran   |

---

## 🛠️ Technologies Used

- Java (Socket Programming)  
- Apache JMeter (Load Testing)  
- `ExecutorService` for thread pooling  

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

- **Single-threaded servers** are simple but highly inefficient under load and especially with file I/O.
- **Multi-threaded servers** handle load better but can miss requests under very high I/O pressure.
- **Thread pooling** provides the most efficient, stable, and scalable performance—especially in I/O-heavy environments.
