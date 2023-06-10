package com.yxy.resume.until;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralBasicToText {
//    /**
//     * 扫描结果转换为文本版本1
//     * @param textDetectionsJson
//     * @return
//     */
//    public static String generalBasicToText(String textDetectionsJson) {
//        ObjectMapper mapper = new ObjectMapper();
//        StringBuffer context= new StringBuffer();
//        try {
//            // First convert textDetectionsJson to a JsonNode
//            JsonNode textDetectionsObject = mapper.readTree(textDetectionsJson);
//
//            // Then extract TextDetections array from this object
//            List<JsonNode> textDetections = mapper.convertValue(textDetectionsObject.get("TextDetections"), new TypeReference<List<JsonNode>>() {});
//
//            Map<Integer, List<JsonNode>> groupedTextDetections = textDetections.stream()
//                    .collect(Collectors.groupingBy(jsonNode -> getParagNo(jsonNode, mapper)));  //将具有相同 "ParagNo" 属性值的 JsonNode 对象放入同一个列表中
//
//            for (Map.Entry<Integer, List<JsonNode>> entry : groupedTextDetections.entrySet()) {
//                List<JsonNode> sortedGroup = entry.getValue().stream()
//                        .sorted(Comparator.comparing(jsonNode -> getY((JsonNode) jsonNode, mapper))
//                                .thenComparing(jsonNode -> getX((JsonNode)jsonNode, mapper)))
//                        .collect(Collectors.toList());
//
//                String mergedText = sortedGroup.stream()
//                        .map(obj -> obj.get("DetectedText").asText())
//                        .collect(Collectors.joining(" "));
//
//                context.append(mergedText);
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String context1=DateUtils.convertDatesAccurateToTheDay(context.toString());
//
//
//
//        return context1.toString().replaceAll("\\r", ";").replaceAll("\\n", ";").replaceAll("\\t", ";").replaceAll(" ", "");
//    }
//
//    private static int getParagNo(JsonNode detection, ObjectMapper mapper) {
//        try {
//            JsonNode advancedInfo = mapper.readTree(detection.get("AdvancedInfo").asText());
//            JsonNode parag = advancedInfo.get("Parag");
//            return parag.get("ParagNo").asInt();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//    private static int getY(JsonNode detection, ObjectMapper mapper) {
//        JsonNode itemPolygon = detection.get("ItemPolygon");
//        return itemPolygon.get("Y").asInt();
//    }
//
//    private static int getX(JsonNode detection, ObjectMapper mapper) {
//        JsonNode itemPolygon = detection.get("ItemPolygon");
//        return itemPolygon.get("X").asInt();
//    }

    /**
     * 扫描结果转换为文本版本2
     * @param json
     * @return
     */
    public static String generalBasicToTextV2(String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TextDetections detections = mapper.readValue(json, TextDetections.class);

            String text = detections.textDetections.stream()
                    .sorted((d1, d2) -> {
                        int cmpY = Integer.compare(d1.polygon.get(0).Y, d2.polygon.get(0).Y);
                        if (cmpY != 0) {
                            return cmpY;
                        } else {
                            return Integer.compare(d1.polygon.get(0).X, d2.polygon.get(0).X);
                        }
                    })
                    .map(d -> d.detectedText)
                    .collect(Collectors.joining("\n"));

            return OptimizeText.optimize(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   static private class Coord {
        public int X;
        public int Y;
    }

    static private class TextDetection {
        @JsonProperty("DetectedText")
        public String detectedText;

        @JsonProperty("Confidence")
        public int confidence;

        @JsonProperty("Polygon")
        public List<Coord> polygon;
    }

    static private class TextDetections {
        @JsonProperty("TextDetections")
        public List<TextDetection> textDetections;
    }


}

