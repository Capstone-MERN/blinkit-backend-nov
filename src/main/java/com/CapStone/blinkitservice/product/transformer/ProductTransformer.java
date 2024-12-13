package com.CapStone.blinkitservice.product.transformer;

import com.CapStone.blinkitservice.product.dto.MetaData;
import com.CapStone.blinkitservice.product.dto.ProductDetailResponseDto;
import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import java.util.*;
import java.util.stream.Collectors;

public class ProductTransformer {


    public static List<ProductResponseDto> productToProductResponse(Page<ProductEntity> products, HashMap<Integer, Integer> quantities) {

        return products.getContent().stream()
                .map(product -> ProductResponseDto.builder()
                        .title(product.getName())
                        .price(product.getPrice() * (100 - product.getDiscount()))
                        .imageUrl(product.getImageUrl())
                        .maxQuantity(product.getMaxOrderLimit())
                        .quantity(!quantities.isEmpty() ? quantities.get(product.getId()) != null ? quantities.get(product.getId()) : 0 : 0)
                        .description(product.getDescription())
                        .discountPercent(product.getDiscount())
                        .originalPrice(product.getPrice())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static ProductSearchResponseDto createProductSearchResponse(Page<ProductEntity> products, HashMap<Integer, Integer> quantities){

        return ProductSearchResponseDto.builder()
                .products(productToProductResponse(products, quantities))
                .size(products.getSize())
                .pageNumber(products.getNumber())
                .hasNextPage(products.hasNext())
                .build();
    }

    public static ProductDetailResponseDto productToProductDetailResponse(ProductEntity product, int quantity) throws Exception {

        MetaData data = transformMetaData(product.getMetaData());
        return ProductDetailResponseDto.builder()
                .id(product.getId())
                .title(product.getName())
                .description(product.getDescription())
                .cartQuantity(quantity)
                .maxQuantityLimit(product.getMaxOrderLimit())
                .gallery(data.getImages())
                .productDetails(data.getDetails())
                .build();

    }

    public static MetaData transformMetaData(Map<String, Object> metaData) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.convertValue(metaData, JsonNode.class);

        MetaData transformedMetaData = new MetaData();
        transformedMetaData.setDetails(new ArrayList<>());
        transformedMetaData.setImages(new ArrayList<>());

        JsonNode galleryNode = rootNode.at("/product/gallery");
        if (galleryNode != null && galleryNode.isObject()) {
            if (galleryNode.has("mainPhoto")) {
                transformedMetaData.getImages().add(galleryNode.get("mainPhoto").asText());
            }
            if (galleryNode.has("clusterPhotos") && galleryNode.get("clusterPhotos").isArray()) {
                galleryNode.get("clusterPhotos").forEach(photo -> transformedMetaData.getImages().add(photo.asText()));
            }
        }

        JsonNode productNode = rootNode.path("product");
        Iterator<Map.Entry<String, JsonNode>> fields = productNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();

            if (!key.equals("gallery")) {
                Map<String, String> detailMap = new HashMap<>();
                detailMap.put(key, value.isValueNode() ? value.asText() : value.toString());
                transformedMetaData.getDetails().add(detailMap);
            }
        }

        return transformedMetaData;
    }
}

