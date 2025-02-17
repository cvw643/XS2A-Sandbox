/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { SendCode } from '../models/send-code';
import { ResetPassword } from '../models/reset-password';
import { UpdatePassword } from '../models/update-password';

/**
 * Provides access to online banking
 */
@Injectable({
  providedIn: 'root',
})
class OnlineBankingAuthorizationService extends __BaseService {
  static readonly loginUsingPOST1Path = '/api/v1/login';
  static readonly sendCodeUsingPOSTPath = '/api/v1/password';
  static readonly updatePasswordUsingPUTPath = '/api/v1/password';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `OnlineBankingAuthorizationService.LoginUsingPOST1Params` containing the following parameters:
   *
   * - `pin`: pin
   *
   * - `login`: login
   */
  loginUsingPOST1Response(params: OnlineBankingAuthorizationService.LoginUsingPOST1Params): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.pin != null) __headers = __headers.set('pin', params.pin.toString());
    if (params.login != null) __headers = __headers.set('login', params.login.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/v1/login`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * @param params The `OnlineBankingAuthorizationService.LoginUsingPOST1Params` containing the following parameters:
   *
   * - `pin`: pin
   *
   * - `login`: login
   */
  loginUsingPOST1(params: OnlineBankingAuthorizationService.LoginUsingPOST1Params): __Observable<null> {
    return this.loginUsingPOST1Response(params).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @param resetPassword resetPassword
   * @return OK
   */
  sendCodeUsingPOSTResponse(resetPassword: ResetPassword): __Observable<__StrictHttpResponse<SendCode>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = resetPassword;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/v1/password`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<SendCode>;
      })
    );
  }
  /**
   * @param resetPassword resetPassword
   * @return OK
   */
  sendCodeUsingPOST(resetPassword: ResetPassword): __Observable<SendCode> {
    return this.sendCodeUsingPOSTResponse(resetPassword).pipe(
      __map(_r => _r.body as SendCode)
    );
  }

  /**
   * @param resetPassword resetPassword
   * @return OK
   */
  updatePasswordUsingPUTResponse(resetPassword: ResetPassword): __Observable<__StrictHttpResponse<UpdatePassword>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = resetPassword;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/v1/password`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UpdatePassword>;
      })
    );
  }
  /**
   * @param resetPassword resetPassword
   * @return OK
   */
  updatePasswordUsingPUT(resetPassword: ResetPassword): __Observable<UpdatePassword> {
    return this.updatePasswordUsingPUTResponse(resetPassword).pipe(
      __map(_r => _r.body as UpdatePassword)
    );
  }
}

module OnlineBankingAuthorizationService {

  /**
   * Parameters for loginUsingPOST1
   */
  export interface LoginUsingPOST1Params {

    /**
     * pin
     */
    pin: string;

    /**
     * login
     */
    login: string;
  }
}

export { OnlineBankingAuthorizationService }
