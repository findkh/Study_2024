interface Params {
  PATNO: string;
  PATID: string;
  PATNAME: string;
  SEX: string;
  AGE: string;
  DEPTNO: string;
  DEPTNAME: string;
  DSCODE: string;
  DSNAME: string;
  DOCTORNAME: string;
  DOCTORCODE: string;
  RSVNO: string;
  RSVDATE: string;
  RSVTIME: string;
  IN_RSVMENO: string;
  QADATA: QADataItem[];
  HISTORY: HistroyItem[];
}

interface QADataItem {
  NO: string;
  QUESTION: string;
  ALTWHEN: string;
  ALTWHAT: string;
}

interface HistroyItem {
  CELLPHONE: string;
  PATNO: string;
  RSVPLACE: string;
  RSVTIMERSVTYPE: string;
  RSVDELYN: string;
  PATNAMEDEPTNAME: string;
  DOCTORNO: string;
  PATIDRSVCHGYN: string;
  DOCTORNAMERSVNO: string;
  DEPTNORSVDATE: string;
}

interface Input {
  callId: string;
  tenant: string;
  params: Params;
}

interface Contents {
  input: Input;
}

export interface InputData {
  msgKey: string;
  contents: Contents;
}
