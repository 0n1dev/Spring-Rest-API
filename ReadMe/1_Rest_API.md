# REST API

- REpresentational State Transfer
- 인터넷 상의 시스템 간의 독립성을 제공하는 방법중 하나
- REST 아키텍처 스타일을 따르는 API

### REST API 아키텍처 스타일

- Client-Server
- Stateless
- Cache
- **Uniform Interface**
- Layered System
- Code-On-Domaind (Optional)

### Uniform Interface

- Identification of resources (리소스가 URI로 식별되면 됨)
- Manipulation of resources through representations (리소스를 만들거나 업데이트 하거나 삭제할 때 HTTP 메서드에 포함 시켜 전송)
- **self-descriptive messages** (메세지는 스스로를 설명해야 함)
- **hypermedia as the engine of application state (HATEOAS)** (애플리케이션의 상태는 Hyperlink를 이용해 전이되어야 함)