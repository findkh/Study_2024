interface Params {
  PATNO: string;
}

interface Output {
  callId: string;
  tenant: string;
  params: Params;
}

interface Contents {
  output: Output;
}

export interface OutputData {
  msgKey: string;
  contents: Contents;
}
