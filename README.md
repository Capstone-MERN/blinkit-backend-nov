# API Endpoint Documentation

## Base URL

https://blinkit-service.onrender.com

### Security Filter

- **Request**: All types of requests
- **Response**:  
    - **Case**: With Token but Invalid Token ID, Without Token but Private API  
        - **Response Code**: `401`
        - **Response Object**: `String`  
        - **Response Body**: `"Error: Sorry, Invalid token"`

### Feature - Authentication

#### 1. **POST /auth/signin**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Request Object**: `SigninRequest`  
    - **Request Payload**:
        ```json
        {
          "email" : "abcd@gmail.com",
          "password" : "abcd"
        }
        ```
- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `SignInResponse`  
        - **Response Body**:  
            ```json
            { 
                "message" : "Login success",
                "token" : "$generated_token"
            }
            ```

    - **catch block**:  
        - **Response Code**: `401`  
        - **Response Object**: `String`  
        - **Response Body**: "Invalid credentials"

#### 2. **POST /auth/signup**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@Valid`  
    - **Request Object**: `SignupRequest`  
    - **Request Payload**:
        ```json
        {
           "name" : "abcd",
           "password" : "abcd",
           "email" : "abcd1234@gmail.com",
           "mobileNumber" : "9876543210"
        }
        ```
- **Response**:  
    - **try block**:  
        - **Response Code**: `201`  
        - **Response Object**: `SignupResponse`  
        - **Response Body**:  
            ```json
            { 
                "message" : "Successfully signed up"
            }
            ```

    - **catch block - Exception Handler - MethodArgumentNotValidException**:  
        - **Response Code**: `400`  
        - **Response Object**: `SignupResponse`  
        - **Response Body**:  
            ```json 
            { 
                "message" : "Signup failed: <field name> must be <validation credential>"
            }
            ```

    - **catch block - DataIntegrityViolationException**:  
        - **Response Code**: `400`  
        - **Response Object**: `NetworkErrorResponse`  
        - **Response Body**:  
            ```json 
            {
                "message" : "Signup failed: Given mobile number already exist. (or) Signup failed: Given email already exist. (or) Signup failed: Given details already exist.",
                "statusCode" : "400"
            }
            ```

    - **catch block - Exception**:  
        - **Response Code**: `500`  
        - **Response Object**: `NetworkErrorResponse`  
        - **Response Body**:  
            ```json 
            { 
                "message" : "Sorry, sign up failed",
                "statusCode" : "500"
            }
            ```

### Feature - Cart

#### 1. **PUT /api/cart/update**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `CartRequest`  
    - **Request Payload**:  
        ```json
        {
            "items": [
                {
                    "productId": 1,
                    "quantity": 2
                },
                {
                    "productId": 2,
                    "quantity": 3
                }
            ]
        }
        ```

- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `GenericResponse -> CartResponse`  
        - **Response Body**:  
            ```json
            {
                "products": [
                    { "productId": 1, "name":"Amul Milk", "imageUrl":"www.abc.com/abc.jpg", "originalPrice:":100, "discountedPrice":85, "maxOrderLimit":10, "description":"xyz", "quantity": 2, "isAvailable":"true"},
                    { "productId": 2, "name":"Amul Butter", "imageUrl":"www.abc.com/abc.jpg", "originalPrice:":80, "discountedPrice":65, "maxOrderLimit":8, "description":"xyz", "quantity": 3, "isAvailable":"true"}
                ],
                "totalWithoutDiscount": 100.00,
                "grandTotal": 90.00,
                "uniqueQuantity": 5,
                "totalQuantity": 10
            }
            ```

    - **catch block - InvalidCartPayloadResponse**:  
        - **Response Code**: `400`  
        - **Response Object**: `GenericResponse -> GenericErrorResponse`  
        - **Response Body**:  
            ```json 
            {
                "error": "e.getLocalizedMessage()"
            }
            ```

    - **catch block - Exception**:  
        - **Response Code**: `500`  
        - **Response Object**: `GenericResponse -> GenericErrorResponse`  
        - **Response Body**:  
            ```json 
            { 
                "error": "e.getLocalizedMessage()"
            }
            ```

#### 2. **GET /api/cart/get**

- **Request**:  
    - **Request Type**: `N/A`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `N/A`  
    - **Request Payload**: `N/A`  

- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `GenericResponse -> CartResponse`  
        - **Response Body**:  
            ```json
            {
                "products": [
                    { "productId": 1, "quantity": 2 },
                    { "productId": 2, "quantity": 3 }
                ],
                "totalWithoutDiscount": 100.00,
                "grandTotal": 90.00,
                "uniqueQuantity": 5,
                "totalQuantity": 10
            }
            ```

    - **catch block - Exception**:  
        - **Response Code**: `500`  
        - **Response Object**: `GenericResponse -> GenericErrorResponse`  
        - **Response Body**:  
            ```json 
            { 
                "error": "e.getLocalizedMessage()"
            }
            ```

### Feature - Collections

#### 1. **GET /auth/collections/getActiveCollections**

- **Request**:  
    - **Request Type**: `N/A`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `N/A`  
    - **Request Payload**: `N/A`

- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `GenericResponse -> CollectionsResponse`  
        - **Response Body**:  
            ```json
            {
                "collections": [
                    {
                        "collectionId": 1,
                        "collectionTitle": "Spring Collection",
                        "products": [
                            { "productId": 1, "productName": "Product A" },
                            { "productId": 2, "productName": "Product B" }
                        ]
                    },
                    {
                        "collectionId": 2,
                        "collectionTitle": "Winter Collection",
                        "products": [
                            { "productId": 3, "productName": "Product C" },
                            { "productId": 4, "productName": "Product D" }
                        ]
                    }
                ]
            }
            ```

    - **catch block - Exception**:  
        - **Response Code**: `500`  
        - **Response Object**: `GenericResponse -> GenericErrorResponse`  
        - **Response Body**:  
            ```json 
            { 
                "error": "e.getLocalizedMessage()"
            }
            ```

### Feature - Order

#### 1. **POST /api/place-order**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@AuthenticationPrincipal, @Valid`  
    - **Request Object**: `OrderRequest`  
    - **Request Payload**:  
        ```json
        {
            "addressId": 1,
            "timestamp": 1635446979000,
            "contactNumber": "1234567890"
        }
        ```

- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `GenericResponse -> OrderResponse`  
        - **Response Body**:  
            ```json
            {
                "orderId": 123,
                "message": "Order placed successfully"
            }
            ```

    - **catch block - Exception Handler - MethodArgumentNotValidException**:  
        - **Response Code**: `400`  
        - **Response Object**: `GenericResponse -> GenericErrorResponse`  
        - **Response Body**:  
            ```json 
            {
                "error": "\"field name\" : \"error message\""
            }
            ```

    - **catch block - Exception**:  
        - **Response Code**: `500`  
        - **Response Object**: `GenericResponse -> GenericErrorResponse`  
        - **Response Body**:  
            ```json 
            { 
                "error": "e.getLocalizedMessage()"
            }
            ```


### Feature - Category

#### 1. **GET /category/all**

- **Request**:  
    - **Request Type**: `N/A`  
    - **Other Annotations**: `N/A`  
    - **Request Object**: `N/A`   
    - **Request Payload**: `N/A`  

- **Response**:  
        - **Response Code**: `200`  
        - **Response Object**: `List<CategoryResponse>`  
        - **Response Body**:  
            ```json
            {
                "categoryId": 1,
                "imageUrl": "www.sample.com/sample.jpg",
                "title": "Category A",
                "defaultSubcategory": {
                      "id": 1,
                      "title": "Sub Category A"
                }
            }
            ```

### Feature - Product

#### 1. **POST /products/v1/search**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `ProductSearchRequestDto`  
    - **Request Payload**:  
        ```json
        {
            "query": "Amul",
            "categoryId": "1",
            "subCategoryId": "2",
            "filter": "RELEVANCE"
        }
        ```

- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `ProductSearchResponseDto`  
        - **Response Body**:  
            ```json
            {
                "hasNextPage": "false",
                "pageNumber": "1",
                "size": "10",
                "products": [
                    { "title": "Amul Milk", "price:":85, "imageUrl":"www.abc.com/abc.jpg", "maxQuantity":10, "quantity": 2, "description":"xyz", "discountPercent":15, "originalPrice": 100},
                    { "title": "Amul Butter", "price:":85, "imageUrl":"www.abc.com/abc.jpg", "maxQuantity":10, "quantity": 2, "description":"xyz", "discountPercent":15, "originalPrice": 100},
                ],
            }
            ```

#### 2. **GET /products/v1/details**

- **Request**:  
    - **Request Type**: `@RequestParam`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `N/A`  
    - **Request Payload**: `/details?id=1`  

- **Response**:  
    - **try block**:  
        - **Response Code**: `200`  
        - **Response Object**: `Object`  
        - **Response Body**:  
            ```json
            {
                "id": 1,
                "title": "Amul Milk",
                "description": "Milk Item",
                "gallery": [
                    "www.abc.com/amul1.jpg", "www.abc.com/amul2.jpg", "www.abc.com/amul3.jpg", "www.abc.com/amul4.jpg",
               ],
                "cartQuantity": 1,
                "maxQuantityLimit": 10,
                "productDetails": [
                    { {"Ingredients":"Pure Cow Milk"}, {"Chemical Free":"Yes"}, {"Storage Condition":"Under 10 degrees"}, {"Expiry":"5 Days from Packing"} },
                ],
            }
            ```
    - **catch block - Exception**:  
        - **Response Code**: `400`  
        - **Response Object**: `Object -> String`  
        - **Response Body**: `$ error message`
     

### Feature - Address

#### 1. **POST /address/v1**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `AddressRequest`  
    - **Request Payload**:  
        ```json
        {
            "latitude": 120390404.09,
            "addressId": "1",
            "longitude": 17273893.08,
            "addressLine1": "ABC",
            "addressLine2": "DEF",
            "addressLine3": "GHI",
            "phoneNo": "9876543210"
        }
        ```

- **Response**:  
    - **try block**:  
        - **Response Code**: `N/A`  
        - **Response Object**: `AddressBookEntity`  
        - **Response Body**:  
            ```json
            {
 
            }
            ```

