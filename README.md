# API Endpoint Documentation

## Base URL

https://______________

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
    - **Request Object**: `AuthRequest`  
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
        - **Response Object**: `String`  
        - **Response Body**: "Bearer <generated_jwt_token>"

    - **catch block**:  
        - **Response Code**: `401`  
        - **Response Object**: `String`  
        - **Response Body**: "Invalid credentials"

#### 2. **POST /auth/signup**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@Valid`  
    - **Request Object**: `UserRequest`  
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
        - **Response Object**: `AuthResponse`  
        - **Response Body**:  
            ```json
            { 
                "message" : "Successfully signed up"
            }
            ```

    - **catch block - Exception Handler - MethodArgumentNotValidException**:  
        - **Response Code**: `400`  
        - **Response Object**: `AuthResponse`  
        - **Response Body**:  
            ```json 
            { 
                "message" : "Signup failed: <field name> must be <validation credential>"
            }
            ```

    - **catch block - DataIntegrityViolationException**:  
        - **Response Code**: `400`  
        - **Response Object**: `AuthResponse`  
        - **Response Body**:  
            ```json 
            {
                "message" : "Signup failed: Given mobile number already exist. (or) Signup failed: Given email already exist. (or) Signup failed: Given details already exist."
            }
            ```

    - **catch block - Exception**:  
        - **Response Code**: `500`  
        - **Response Object**: `AuthResponse`  
        - **Response Body**:  
            ```json 
            { 
                "message" : "Sorry, sign up failed"
            }
            ```

### Feature - Cart

#### 1. **GET /api/cart/update**

- **Request**:  
    - **Request Type**: `@RequestBody`  
    - **Other Annotations**: `@AuthenticationPrincipal`  
    - **Request Object**: `UpdateCartRequest`  
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
        - **Response Object**: `GenericResponse -> UpdateCartResponse`  
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

#### 5. **POST /api/place-order**

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




