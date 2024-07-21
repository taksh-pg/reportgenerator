package com.natwest.pravesh.test.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.OutputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import com.natwest.pravesh.service.TransformationService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransformationServiceTest {

    @Test
    void transform() {
        TransformationService service = new TransformationService();

        InputRecord input = new InputRecord();
        input.setField1("field1");
        input.setField2("field2");
        input.setField5(10.0);
        input.setRefkey1("key1");
        input.setRefkey2("key2");

        ReferenceRecord reference = new ReferenceRecord();
        reference.setRefkey1("key1");
        reference.setRefkey2("key2");
        reference.setRefdata1("refdata1");
        reference.setRefdata2("refdata2");
        reference.setRefdata3("refdata3");
        reference.setRefdata4(5.0);

        List<InputRecord> inputRecords = List.of(input);
        List<ReferenceRecord> referenceRecords = List.of(reference);

        List<OutputRecord> outputRecords = service.transform(inputRecords, referenceRecords);

        assertEquals(1, outputRecords.size());
        OutputRecord output = outputRecords.get(0);
        assertEquals("field1field2", output.getOutfield1());
        assertEquals("refdata1", output.getOutfield2());
        assertEquals("refdata2refdata3", output.getOutfield3());
        assertEquals(100.0, output.getOutfield4());
        assertEquals(10.0, output.getOutfield5());
    }
}
