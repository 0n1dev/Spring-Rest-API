# Spring HATEOAS

> REST하게 리소스를 제공하기 위한 API를 만들기 위해 사용하는 라이브러리

- 링크를 만드는 기능
    - 문자열 가지고 만들기
    - 컨트롤러와 메소드로 만들기
- 리소스를 만드는 기능
    - 데이터 + 링크
- 링크를 찾아주는 기능
    - Traverson
    - LinkDiscoverers
- 링크
    - HREF (하이퍼 링크 URI)
    - REL (리소스와의 관계)
        - self
        - profile
        
 ---
 
 HATEOAS 버전이 바뀌면서 변경된것
 
 - `ResourceSupport` changed to `RepresentationModel`
 - `Resource` changed to `EntityModel`
 - `Resources` changed to `CollectionModel`
 - `PagedResources` changed to `PagedModel`
 - `ResourceAssembler` changed to `RepresentationModelAssembler`