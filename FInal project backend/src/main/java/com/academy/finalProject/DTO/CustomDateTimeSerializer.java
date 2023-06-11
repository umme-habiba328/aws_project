package com.academy.finalProject.DTO;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.hibernate.type.TimeType;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateTimeSerializer extends StdSerializer<TimeType>{

	private SimpleDateFormat formatter 
    = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  public CustomDateTimeSerializer() {
      this(null);
  }

  public CustomDateTimeSerializer(Class t) {
      super(t);
  }
  
  @Override
  public void serialize (TimeType value, JsonGenerator gen, SerializerProvider arg2)
    throws IOException, JsonProcessingException {
      gen.writeString(formatter.format(value));
  }
}
