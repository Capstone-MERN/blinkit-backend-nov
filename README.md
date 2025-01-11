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

## POST /auth/signin

### Request
```json
{
  "email" : "abcd@gmail.com",
  "password" : "abcd"
}
```
### Response 
#### Success ( 200 )
```json
{ 
    "message" : "Login success",
    "token" : "$generated_token"
}
```

#### BadRequest ( 400 )
```json
{
  "message": "Invalid credentials",
  "statusCode": 400
}
```

#### Internal Server Error ( 500 )
```json 
{ 
    "message" : "Sorry, sign up failed",
    "statusCode" : "500"
}
```

## POST /auth/signup

### Request
#### Request Payload
```json
{
   "name" : "abcd",
   "password" : "abcd",
   "email" : "abcd1234@gmail.com",
   "mobileNumber" : "9876543210"
}
```
### Response
#### Success ( 201 )
```json
{ 
    "message" : "Successfully signed up"
}
```
#### Bad Request ( 400 )
```json 
{ 
    "message" : "Signup failed: <field name> must be <validation credential>"
}
```
#### Internal Server Error ( 500 )
```json 
{ 
    "message" : "Sorry, sign up failed",
    "statusCode" : "500"
}
```

### Feature - Cart

## PUT /api/cart/update

### Request
#### Request Payload 
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

### Response
#### Success ( 200 )
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

#### BadRequest (400)
**If invalid payload is sent**
```json 
{
    "error": "e.getLocalizedMessage()"
}
```
#### Internal Server Error ( 500 )
```json 
{ 
    "message" : "Sorry, sign up failed",
    "statusCode" : "500"
}
```

## GET /api/cart/get
Private API

### Request
#### Request Header should contain token

```json
{
  "Authorization": "Bearer ${token}"
}
```
No Payload required

### Response
#### Success ( 200 )
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
#### Internal Server Error ( 500 )
```json 
{ 
    "message" : "Sorry, sign up failed",
    "statusCode" : "500"
}
```

### Feature - Collections

## GET /auth/collections/getActiveCollections

### Request
No payload required
### Response
#### Success ( 200 )
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
#### Internal Server Error ( 500 )
```json 
{ 
    "message" : "Sorry, sign up failed",
    "statusCode" : "500"
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

## GET /category/all

### Request
**No Payload required**
### Response
#### Success (200)
```json
[
  {
    "categoryId": 1,
    "imageUrl": "www.sample.com/sample.jpg",
    "title": "Category A",
    "defaultSubcategory": {
      "id": 1,
      "title": "Sub Category A"
    }
  }
]
```

#### Internal Server Error ( 500 )
```json 
{ 
    "message" : "Sorry, sign up failed",
    "statusCode" : "500"
}
```


### Feature - Product

## POST /products/search

### Request
```json
{
    "query": "Amul",
    "pageNumber": "1",
    "subCategoryId": "2",
    "filter": "RELEVANCE"
}
```

### Response
#### Success ( 200 )
```json
{
    "hasNextPage": "false",
    "pageNumber": "1",
    "size": "20",
    "products": [
        { 
          "productId": 21,
          "title": "Amul Milk", 
          "price:":85, 
          "imageUrl":"www.abc.com/abc.jpg", 
          "maxQuantity":10,
          "quantity": 2,
          "description":"xyz", 
          "discountPercent":15, 
          "originalPrice": 100
        },
        {
          "productId": 22,
          "title": "Amul Butter", 
          "price:":85, 
          "imageUrl":"www.abc.com/abc.jpg", 
          "maxQuantity":10, 
          "quantity": 2, 
          "description":"xyz", 
          "discountPercent":15, 
          "originalPrice": 100
        }
    ]
}
```

## GET /products/details?id=3893

### Request
No payload required

### Response ( 200 )
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
      {"Ingredients":"Pure Cow Milk"}, 
      {"Chemical Free":"Yes"}, 
      {"Storage Condition":"Under 10 degrees"}, 
      {"Expiry":"5 Days from Packing"}
    ]
}
```
#### BadRequest ( 400 )
TODO: implement this response
**If invalid productId is sent**
```json 
{
    "error": "e.getLocalizedMessage()"
}
```
     

### Feature - Address
TODO: complete this
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