package values.databinding;

import androidx.databinding.MergedDataBinderMapper;public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.example.test.DataBinderMapperImpl());
  }
}
