/* tslint:disable */
import { AmountTO } from './amount-to';
import { AccountReferenceTO } from './account-reference-to';
import { ExchangeRateTO } from './exchange-rate-to';
export interface TransactionTO {
  amount?: AmountTO;
  bankTransactionCode?: string;
  bookingDate?: string;
  checkId?: string;
  creditorAccount?: AccountReferenceTO;
  creditorId?: string;
  creditorName?: string;
  debtorAccount?: AccountReferenceTO;
  debtorName?: string;
  endToEndId?: string;
  entryReference?: string;
  exchangeRate?: Array<ExchangeRateTO>;
  mandateId?: string;
  proprietaryBankTransactionCode?: string;
  purposeCode?: string;
  remittanceInformationStructured?: string;
  remittanceInformationUnstructured?: string;
  transactionId?: string;
  ultimateCreditor?: string;
  ultimateDebtor?: string;
  valueDate?: string;
}
