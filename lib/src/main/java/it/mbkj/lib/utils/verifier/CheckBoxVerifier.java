package it.mbkj.lib.utils.verifier;

import com.github.yoojia.inputs.EmptyableVerifier;

public class CheckBoxVerifier extends EmptyableVerifier {
    public CheckBoxVerifier() {
    }

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return Boolean.valueOf(notEmptyInput);
    }


}
