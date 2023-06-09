package com.yxy.resume.until;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralBasicToText {

    public static String generalBasicToText(String textDetectionsJson) {
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer context= new StringBuffer();
        try {
            // First convert textDetectionsJson to a JsonNode
            JsonNode textDetectionsObject = mapper.readTree(textDetectionsJson);

            // Then extract TextDetections array from this object
            List<JsonNode> textDetections = mapper.convertValue(textDetectionsObject.get("TextDetections"), new TypeReference<List<JsonNode>>() {});

            Map<Integer, List<JsonNode>> groupedTextDetections = textDetections.stream()
                    .collect(Collectors.groupingBy(jsonNode -> getParagNo(jsonNode, mapper)));  //将具有相同 "ParagNo" 属性值的 JsonNode 对象放入同一个列表中

            for (Map.Entry<Integer, List<JsonNode>> entry : groupedTextDetections.entrySet()) {
                List<JsonNode> sortedGroup = entry.getValue().stream()
                        .sorted(Comparator.comparing(jsonNode -> getY((JsonNode) jsonNode, mapper))
                                .thenComparing(jsonNode -> getX((JsonNode)jsonNode, mapper)))
                        .collect(Collectors.toList());

                String mergedText = sortedGroup.stream()
                        .map(obj -> obj.get("DetectedText").asText())
                        .collect(Collectors.joining(" "));

                context.append(mergedText);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        String context1=DateUtils.replaceDateFormats(context.toString());

        System.out.println(context);

        return context1.toString().replaceAll("\\r", ";").replaceAll("\\n", ";").replaceAll("\\t", ";").replaceAll(" ", "");
    }

    private static int getParagNo(JsonNode detection, ObjectMapper mapper) {
        try {
            JsonNode advancedInfo = mapper.readTree(detection.get("AdvancedInfo").asText());
            JsonNode parag = advancedInfo.get("Parag");
            return parag.get("ParagNo").asInt();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static int getY(JsonNode detection, ObjectMapper mapper) {
        JsonNode itemPolygon = detection.get("ItemPolygon");
        return itemPolygon.get("Y").asInt();
    }

    private static int getX(JsonNode detection, ObjectMapper mapper) {
        JsonNode itemPolygon = detection.get("ItemPolygon");
        return itemPolygon.get("X").asInt();
    }
}