public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    String text = "";
    int length;
    String tOutput = "";
    Double sum;
    String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listener();
    }

    private void listener() {
        binding.btn0.setOnClickListener(this);
        binding.btn00.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
        binding.btn7.setOnClickListener(this);
        binding.btn8.setOnClickListener(this);
        binding.btn9.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnMinus.setOnClickListener(this);
        binding.btnModule.setOnClickListener(this);
        binding.btnMultiply.setOnClickListener(this);
        binding.btnEqual.setOnClickListener(this);
        binding.btnDot.setOnClickListener(this);
        binding.btnDivide.setOnClickListener(this);
        binding.btnPlus.setOnClickListener(this);
        binding.btnBackspace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btn0) {
            binding.input.setText(text + "0");
        } else if (view == binding.btn1) {
            binding.input.setText(text + "1");
        } else if (view == binding.btn2) {
            binding.input.setText(text + "2");
        } else if (view == binding.btn3) {
            binding.input.setText(text + "3");
        } else if (view == binding.btn4) {
            binding.input.setText(text + "4");
        } else if (view == binding.btn6) {
            binding.input.setText(text + "6");
        } else if (view == binding.btn5) {
            binding.input.setText(text + "5");
        } else if (view == binding.btn7) {
            binding.input.setText(text + "7");
        } else if (view == binding.btn8) {
            binding.input.setText(text + "8");
        } else if (view == binding.btn9) {
            binding.input.setText(text + "9");
        } else if (view == binding.btn00) {
            binding.input.setText(text + "00");
        } else if (view == binding.btnDot) {
            if (validation())
                binding.input.setText(text + ".");
        } else if (view == binding.btnDivide) {
            if (validation())
                binding.input.setText(text + "÷");
        } else if (view == binding.btnMultiply) {
            if (validation())
                binding.input.setText(text + "x");
        } else if (view == binding.btnMinus) {
            if (validation())
                binding.input.setText(text + "-");
        } else if (view == binding.btnModule) {
            if (validation())
                binding.input.setText(text + "%");
        } else if (view == binding.btnEqual) {
            if (validation())
                binding.input.setText(text + "=");
        } else if (view == binding.btnClear) {
            binding.input.setText("");
            binding.output.setText("");

        } else if (view == binding.btnPlus) {
            if (validation())
                binding.input.setText(text + "+");

        } else if (view == binding.btnBackspace) {
            if (!text.isEmpty()) {
                text = binding.input.getText().subSequence(0, length - 1).toString();
                binding.input.setText(text);
                if (binding.input.getText().toString().isEmpty())
                    binding.output.setText("");
            }
        }
        text = binding.input.getText().toString();
        length = text.length();
        if (validate()) {
            text = text.replace("=", "").replace("x", "*").replace("÷", "/");
            array = text.split("(?=[-+%*/])");
            validateText();
        }
    }

    private boolean validate() {
        if (!text.isEmpty() && !text.endsWith("+") && !text.endsWith("/") && !text.endsWith("*")
                && !text.endsWith("-") && !text.endsWith("x") && !text.endsWith("%")
                && !text.endsWith("=") && !text.endsWith("÷") && !text.endsWith(".") && text.length() >1)
            return true;
        return false;
    }

    private void validateText() {
        sum = Double.valueOf(array[0]);

        for (int i = 0; i < array.length; i++){
            System.out.println("Array value "+array[i]);
            if (array[i].contains("+")){
                sum += Double.valueOf(array[i]);

            }else if (array[i].contains("-")){
                sum += Double.valueOf(array[i]);

            }else if (array[i].contains("*")){
                array[i] = array[i].replace("*", "");
                sum *= Double.valueOf(array[i]);

            }else if (array[i].contains("/")){
                array[i] = array[i].replace("/", "");
                sum /= Double.valueOf(array[i]);

            }else if (array[i].contains("%")){
                array[i] = array[i].replace("%", "");
                sum %= Double.valueOf(array[i]);
            }
            tOutput = sum+"".replace("/", "÷").replace("*", "x");
            binding.output.setText("= "+sum);
        }
    }

    private boolean validation(){
        if(!text.isEmpty() && text.charAt(length - 1) != '÷' && text.charAt(length - 1) != 'x'
                && text.charAt(length - 1) != '+' && text.charAt(length - 1) != '='
                && text.charAt(length - 1) != '-' && text.charAt(length - 1) != '.')
            return true;
        return false;
    }

}