package com.natwest.pravesh.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.OutputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class TransformationService {

    public List<OutputRecord> transform(List<InputRecord> inputRecords, List<ReferenceRecord> referenceRecords) {
        Map<String, ReferenceRecord> referenceMap = referenceRecords.stream()
                .collect(Collectors.toMap(
                        record -> record.getRefkey1() + "-" + record.getRefkey2(),
                        record -> record
                ));

        return inputRecords.stream()
                .map(input -> {
                    ReferenceRecord reference = referenceMap.get(input.getRefkey1() + "-" + input.getRefkey2());
                    return transformRecord(input, reference);
                })
                .collect(Collectors.toList());
    }

    private OutputRecord transformRecord(InputRecord input, ReferenceRecord reference) {
        OutputRecord output = new OutputRecord();
        output.setOutfield1(input.getField1() + input.getField2());
        output.setOutfield2(reference != null ? reference.getRefdata1() : "");
        output.setOutfield3(reference != null ? reference.getRefdata2() + reference.getRefdata3() : "");
        output.setOutfield4(reference != null ? input.getField5() * Math.max(input.getField5(), reference.getRefdata4()) : null);
        output.setOutfield5(reference != null ? Math.max(input.getField5(), reference.getRefdata4()) : null);
        return output;
    }
}